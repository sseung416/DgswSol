package com.example.a2ndproject.ui.viewmodel.fragment

import android.app.Application
import android.net.Uri
import android.text.Editable
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a2ndproject.R
import com.example.a2ndproject.ui.view.utils.*
import com.example.domain.usecase.user.GetDuplicateIdCheckUseCase
import com.example.domain.usecase.user.PostSignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val application: Application,
    private val getDuplicateIdCheckUseCase: GetDuplicateIdCheckUseCase,
    private val postSignUpUseCase: PostSignUpUseCase,
): ViewModel() {

    val id = MutableLiveData("")
    val password = MutableLiveData("")
    val idCheck = MutableLiveData(true) //todo false 로 바꾸기~

    val residentNumber = MutableLiveData("")
    val residentNumberBack = MutableLiveData("")
    val name = MutableLiveData("")
    val phoneNumber = MutableLiveData("")

    val profile: MutableLiveData<Uri> = MutableLiveData()
    val nickname = MutableLiveData("")
    val agree = MutableLiveData(false)

    // 에러 메시지
    val errorMsg = MutableLiveData("")

    val residentNumberError = MutableLiveData("")
    val phoneNumberError = MutableLiveData("")
    val nameError = MutableLiveData("")

    val nickNameError = MutableLiveData("")

    val currentItem = MutableLiveData(0)

    // 통신 결과
    private val _isFailure = MutableLiveData("")
    val isFailure = _isFailure

    private val _isSuccessGetIdCheck: MutableLiveData<String> = MutableLiveData()
    val isSuccessIdCheck = _isSuccessGetIdCheck

    private val _isSuccessPostSignUp: MutableLiveData<String> = MutableLiveData()
    val isSuccessPostSignUp = _isSuccessPostSignUp

    private val _isSuccessPostQuickSignUp: MutableLiveData<String> = MutableLiveData()
    val isSuccessPostQuickSignUp = _isSuccessPostQuickSignUp

    /* 회원가입 다음 화면으로 전환 */
    fun navigateToNext() {
        when (currentItem.value) {
            0 -> {
                if (errorMsg.value == "") {
                    if (idCheck.value!!) {
                        currentItem.value = 1
                    } else {
                        errorMsg.value = "아이디 중복 확인을 해주세요."
                    }
                } else {
                    // todo errorHandling - 진동, 애니메이션 등
                }
            }

            1 -> {
                if (errorMsg.value == "") {
                    if (idCheck.value!!) {
                        currentItem.value = 2
                    } else {
                        errorMsg.value = "아이디 중복 확인을 해주세요."
                    }
                } else {
                    // todo errorHandling - 진동, 애니메이션 등
                }
            }

            2 -> {

            }

            3 -> {

            }

            4 -> {

            }

            5 -> {
                val list = listOf(
                    residentNumber.value,
                    phoneNumber.value,
                    name.value,
                )
                val errList = listOf(
                    residentNumberError.value,
                    phoneNumberError.value,
                    nameError.value
                )

                if (list.isNotBlankAll() && errList.isBlankAll())
                    currentItem.value = 2
            }

            7 -> {
                signUp()
//                if (!agree.value!! && nickname.value!!.isNotBlank()) {
//                    currentItem.value = 3
//                }
            }

            else -> Log.d("signUp", "오잉? 웨안돼지")
        }

    }

    /* 회원가입 두 번째 화면 */
    fun setResidentNumberError(editable: Editable?) {
        val number = residentNumber.value!!
        val numberBack = residentNumberBack.value!!

        residentNumberError.value = when {
            number.isBlank() || numberBack.isBlank() ->
                getStringErrorInputMsg("주민등록번호를")

            else -> ""
        }
    }

    fun setPhoneNumberError(editable: Editable?) {
        val phoneNumber = residentNumber.value!!

        phoneNumberError.value = when {
            phoneNumber.isBlank() ->
                getStringErrorInputMsg("휴대폰 번호를")

            else -> ""
        }
    }

    fun setNameError(editable: Editable?) {
        val name = name.value!!

        nameError.value = when {
            name.isBlank() ->
                getStringErrorInputMsg("이름을")

            else -> ""
        }
    }

    /* 회원가입 마지막 화면 */
    fun setNicknameError(editable: Editable?) {
        val nickname = nickname.value!!

        /* 영어, 한글, 숫자의 2글자 이상의 조합의 정규식 */
        val reg = "^[a-zA-Z가-힣[0-9]]{2,100}".toRegex()

        nickNameError.value = when {
            nickname.isBlank() ->
                getStringErrorInputMsg("별명을")

            !nickname.matches(reg) ->
                this.getString(application, R.string.error_not_regular_nickname)

            else -> ""
        }
    }

    fun checkId() {
        viewModelScope.launch {
            val id = id.value!!

            try {
                val msg = getDuplicateIdCheckUseCase.buildUseCase(GetDuplicateIdCheckUseCase.Params(id))

                _isSuccessGetIdCheck.postValue(msg.msg!!)
            } catch (e: Exception) {
                Log.d("checkId", e.message.toString())
                _isFailure.postValue(e.message)
            }
        }
    }

    private fun signUp() {
        val id = id.value!!
        val pw = password.value!!
        val phone = phoneNumber.value!!.replace("-", "")
        val birth = residentNumber.value + residentNumberBack.value
        val name = name.value!!
        val attachment = profile.value?:Uri.EMPTY
        val nickName = nickname.value!!

        viewModelScope.launch {
            try {
                val token = postSignUpUseCase.buildUseCase(PostSignUpUseCase.Params(
                    id.getRequestBody(),
                    pw.getRequestBody(),
                    phone.getRequestBody(),
                    birth.getRequestBody(),
                    name.getRequestBody(),
                    nickName.getRequestBody(),
                    attachment.getImageBody("attachment", application.contentResolver))
                )

                _isSuccessPostSignUp.postValue(token.msg!!)
            } catch (e: Exception) {
                Log.d("signUp-failure", e.message.toString())
                e.printStackTrace()
                _isFailure.postValue(e.message.toString())
            }
        }
    }

    fun signUpQuick() {

    }

    /* '~를 입력하세요.' 에러 메세지를 가져오는 메서드 */
    private fun getStringErrorInputMsg(s: String) =
        s + this.getString(application, R.string.error_input)

}