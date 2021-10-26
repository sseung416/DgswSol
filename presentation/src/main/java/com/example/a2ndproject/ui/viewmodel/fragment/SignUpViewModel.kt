package com.example.a2ndproject.ui.viewmodel.fragment

import android.app.Application
import android.text.Editable
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a2ndproject.R
import com.example.a2ndproject.ui.view.base.BaseViewModel
import com.example.a2ndproject.ui.view.utils.*
import com.example.domain.entity.request.QuickPw
import com.example.domain.entity.request.SignUp
import com.example.domain.usecase.user.GetDuplicateIdCheckUseCase
import com.example.domain.usecase.user.PostQuickSignUpUseCase
import com.example.domain.usecase.user.PostSignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val application: Application,
    private val getDuplicateIdCheckUseCase: GetDuplicateIdCheckUseCase,
    private val postSignUpUseCase: PostSignUpUseCase,
    private val postQuickSignUpUseCase: PostQuickSignUpUseCase
): ViewModel() {

    val id = MutableLiveData("")
    val password = MutableLiveData("")
    val idCheck = MutableLiveData(false)

    val residentNumber = MutableLiveData("")
    val residentNumberBack = MutableLiveData("")
    val name = MutableLiveData("")
    val phoneNumber = MutableLiveData<String>()

    val profile = MutableLiveData("")
    val nickname = MutableLiveData("")
    val agree = MutableLiveData(false)

    // 에러 메시지
    val idError = MutableLiveData("")
    val passwordError = MutableLiveData("")

    val residentNumberError = MutableLiveData("")
    val phoneNumberError = MutableLiveData("")
    val nameError = MutableLiveData("")

    val nickNameError = MutableLiveData("")

    val currentItem = MutableLiveData(0)

    // 통신 결과
    private val _isSuccessGetIdCheck: MutableLiveData<String> = MutableLiveData()
    val isSuccessIdCheck = _isSuccessGetIdCheck
//
//    private val _isFailureGetIdCheck: MutableLiveData<GetDuplicateIdCheckUseCase> = MutableLiveData()
//    val isFailureIdCheck = _isFailureGetIdCheck
//
    private val _isSuccessPostSignUp: MutableLiveData<PostSignUpUseCase> = MutableLiveData()
    val isSuccessPostSignUp = _isSuccessPostSignUp
//
//    private val _isFailurePostSignUp: MutableLiveData<PostSignUpUseCase> = MutableLiveData()
//    val isFailurePostSignUp = _isFailurePostSignUp
//
    private val _isSuccessPostQuickSignUp: MutableLiveData<PostQuickSignUpUseCase> = MutableLiveData()
    val isSuccessPostQuickSignUp = _isSuccessPostQuickSignUp
//
//    private val _isFailurePostQuickSignUp: MutableLiveData<PostQuickSignUpUseCase> = MutableLiveData()
//    val isFailurePostQuickSignUp = _isFailurePostQuickSignUp


    /* 회원가입 다음 화면으로 전환 */
    fun navigateToNext() {
        when (currentItem.value) {
            0 -> {
                val list = listOf(id.value, password.value)
                val errList = listOf(idError.value, passwordError.value)

                if (idCheck.value!! && list.isNotBlankAll() && errList.isBlankAll())
                    currentItem.value = 1
            }

            1 -> {
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

            2 -> {
                val list = listOf(profile.value, nickname.value)


                if (agree.value!! && list.isNotBlankAll()) {

                    currentItem.value = 3
                }
            }

            else -> Log.d("signUp", "오잉? 웨안돼지")
        }

    }

    /* 회원가입 첫 번째 화면 */
    fun setIdError(s: Editable?) {
        val id = id.value!!

        /* 영어, 숫자를 포함한 3~12글자의 정규식 */
        val reg = "^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z[0-9]]{3,12}".toRegex()

        this.idError.value = when {
            id.isEmpty() ->
                getStringErrorInputMsg("아이디를")

            !id.matches(reg) ->
                this.getString(application, R.string.error_not_regular_id)

            else -> ""
        }
    }

    fun setPasswordError(editable: Editable?) {
        val password = password.value!!

        /* 영어, 숫자, 특수기호를 모두 포함한 8~12글자의 정규식 */
        val reg = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&])[A-Za-z[0-9]$@$!%*#?&]{8,12}$".toRegex()

        this.passwordError.value = when {
            password.isBlank() ->
                getStringErrorInputMsg("비밀번호를")

            !password.matches(reg) ->
                this.getString(application, R.string.error_not_regular_password)

            else -> ""
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

                _isSuccessGetIdCheck.postValue(msg)
            } catch (e: Exception) {
                MessageUtil.printLog("checkId-viewModel", e.message.toString())
            }
        }
    }

    fun signUp() {
        val id = id.value!!
        val pw = password.value!!
        val phone = phoneNumber.value!!
        val birth = residentNumber.value + residentNumberBack.value
        val name = name.value!!
        val attachment = File("C:\\")
        val nickName = nickname.value!!

        viewModelScope.launch {
            try {
                postSignUpUseCase.buildUseCase(PostSignUpUseCase.Params(
                    id.getRequestBody(),
                    pw.getRequestBody(),
                    phone.getRequestBody(),
                    birth.getRequestBody(),
                    name.getRequestBody(),
                    nickName.getRequestBody(),
                    attachment.toUri().getImageBody("attachment", application.contentResolver)
                ))
            } catch (e: Exception) {

            }
        }


//        val signUp = SignUp(
//            id.value!!,
//            password.value!!,
//            phoneNumber.value!!.replace("-",""),
//            bir
//        )
//
//        postSignUpUseCase.buildUseCase(PostSignUpUseCase.Params())
    }

    fun quickSignUp() {
        viewModelScope.launch {
            val quickPw = QuickPw("123456")

            try {
                postQuickSignUpUseCase!!.buildUseCase(PostQuickSignUpUseCase.Params(quickPw))
                MessageUtil.printLog("quickSignup", "성공")
            } catch (e: Exception) {
                MessageUtil.printLog("quickSignup", "실패")
            }
        }
    }

    /* '~를 입력하세요.' 에러 메세지를 가져오는 메서드 */
    private fun getStringErrorInputMsg(s: String) = ""
//        s + this.getString(application, R.string.error_input)

}