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
import com.example.domain.usecase.user.PostQuickSignUpUseCase
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

    val id = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val idCheck = MutableLiveData(false)

    val residentNumber = MutableLiveData<String>()
    val residentNumberBack = MutableLiveData<String>()
    val name = MutableLiveData<String>()
    val phoneNumber = MutableLiveData<String>()

    val profile: MutableLiveData<Uri> = MutableLiveData()
    val nickname = MutableLiveData("")
    val agree = MutableLiveData(false)

    // 에러 메시지
    val idErr = MutableLiveData("")
    val pwErr = MutableLiveData("")

    val residentNumErr = MutableLiveData("")
    val nameErr = MutableLiveData("")
    val phoneNumErr = MutableLiveData("")

    val nicknameErr = MutableLiveData("")

    val errorMsg = MutableLiveData("")

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
                        currentItem.value = currentItem.value!! + 1
                    } else {
                        errorMsg.value = "아이디 중복 확인을 해주세요."
                    }
                } else {
                    // todo errorHandling - 진동, 애니메이션 등
                }
            }

            1 -> {
                if (errorMsg.value == "") {
                    if (idCheck.value!! && errorMsg.value.isNullOrBlank()) {
                        currentItem.value = currentItem.value!! + 1
                    } else {
                        errorMsg.value = "아이디 중복 확인을 해주세요."
                    }
                } else {
                    // todo errorHandling - 진동, 애니메이션 등
                }
            }

            2 -> {
                if (errorMsg.value == "") {
                    currentItem.value = currentItem.value!! + 1
                }
            }

            3 -> {
                val isNotBlank = listOf(residentNumber.value, residentNumberBack.value).isNotBlankAll()

                if (errorMsg.value == "" && isNotBlank) {
                    currentItem.value = currentItem.value!! + 1
                }
            }

            4 -> {

            }

            5 -> {

            }

            5 -> {
                signUp()
//                if (!agree.value!! && nickname.value!!.isNotBlank()) {
//                    currentItem.value = 3
//                }
            }

            else -> Log.d("SignUpViewModel", "navigateToNext(): 비정상적인 current 값")
        }

    }

//    fun next

    /* 회원가입 마지막 화면 */
    fun setNicknameError(editable: Editable?) {
        val nickname = nickname.value!!

        /* 영어, 한글, 숫자의 2글자 이상의 조합의 정규식 */
        val reg = "^[a-zA-Z가-힣[0-9]]{2,100}".toRegex()

        nickNameError.value = when {
            nickname.isBlank() -> ""
//                getStringErrorInputMsg("별명을")

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

    fun signUp() {
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

    fun addCurrentItem() {
        if (errorMsg.value == "") {
            currentItem.value = currentItem.value!! + 1
        }
    }

    fun setErrorMsg(error: String) {
    }
}