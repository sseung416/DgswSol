package com.example.a2ndproject.ui.viewmodel.fragment

import android.app.Application
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.a2ndproject.R
import com.example.a2ndproject.ui.view.base.BaseViewModel
import com.example.a2ndproject.ui.view.utils.getString
import com.example.domain.entity.request.SignUp
import com.example.domain.usecase.user.GetDuplicateIdCheckUseCase
import com.example.domain.usecase.user.PostQuickSignUpUseCase
import com.example.domain.usecase.user.PostSignUpUseCase

class SignUpViewModel(
    application: Application,
    private val postSignUpUseCase: PostSignUpUseCase? = null,
    private val postQuickSignUpUseCase: PostQuickSignUpUseCase? = null,
    private val getDuplicateIdCheckUseCase: GetDuplicateIdCheckUseCase? = null
) : AndroidViewModel(application) {

    val id = MutableLiveData("")
    val password = MutableLiveData("")

    val residentNumber = MutableLiveData("")
    val residentNumberBack = MutableLiveData("")
    val name = MutableLiveData("")

    val phoneNumber = MutableLiveData<String>()
    val nickname = MutableLiveData<String>()
    val agree = MutableLiveData(false)

    // 에러 메시지
    val idError = MutableLiveData("")
    val passwordError = MutableLiveData("")

    val residentNumberError = MutableLiveData("")
    val phoneNumberError = MutableLiveData("")
    val nameError = MutableLiveData<String>()

    val nickNameError = MutableLiveData("")

//    val duplicateCheckId = MutableLiveData("")

    val currentItem = MutableLiveData(0)

    /* 회원가입 다음 화면으로 전환 */
    fun navigateToNext() {
        when (currentItem.value) {
            0 -> {
                val list = listOf(id.value, password.value, idError.value, passwordError.value)

                if (isNotEmpty(list))
                    currentItem.value = 1
            }

            1 -> {
                val list = listOf(residentNumber.value,
                    phoneNumber.value,
                    name.value,
                    residentNumberError.value,
                    phoneNumberError.value,
                    nameError.value
                )

                if (isNotEmpty(list))
                    currentItem.value = 2
            }

            2 -> {

            }
        }

    }

    /* 회원가입 첫 번째 화면 */
    fun setIdError() {
        val id = id.value!!

        /* 영어, 숫자를 포함한 3~12글자의 정규식 */
        val reg = "^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z[0-9]]{3,12}".toRegex()

        this.idError.value = when {
            id.isEmpty() ->
                getStringErrorInputMsg("아이디를")

            !id.matches(reg) ->
                this.getString(getApplication(), R.string.error_not_regular_id)

            else -> ""
        }
    }

    fun setPasswordError() {
        val password = password.value!!

        /* 영어, 숫자, 특수기호를 모두 포함한 8~12글자의 정규식 */
        val reg = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&])[A-Za-z[0-9]$@$!%*#?&]{8,12}$".toRegex()

        this.passwordError.value = when {
            password.isBlank() ->
                getStringErrorInputMsg("비밀번호를")

            !password.matches(reg) ->
                this.getString(getApplication(), R.string.error_not_regular_password)

            else -> ""
        }
    }

    /* 회원가입 두 번째 화면 */
    fun setResidentNumberError() {
        val number = residentNumber.value!!
        val numberBack = residentNumberBack.value!!

        residentNumberError.value = when {
            number.isBlank() || numberBack.isBlank() ->
                getStringErrorInputMsg("주민등록번호를")

            else -> ""
        }
    }

    fun setPhoneNumberError() {
        val phoneNumber = residentNumber.value!!

        phoneNumberError.value = when {
            phoneNumber.isBlank() ->
                getStringErrorInputMsg("휴대폰 번호를")

            else -> ""
        }
    }

    fun setNameError() {
        val name = name.value!!

        nameError.value = when {
            name.isBlank() ->
                getStringErrorInputMsg("이름을")

            else -> ""
        }
    }

    /* 회원가입 마지막 화면 */
    fun setNicknameError() {
        val nickname = nickname.value!!

        /* 영어, 한글, 숫자의 2글자 이상의 조합의 정규식 */
        val reg = "^[a-zA-Z가-힣[0-9]]{2,100}".toRegex()

        nickNameError.value = when {
            nickname.isBlank() ->
                getStringErrorInputMsg("별명을")

            !nickname.matches(reg) ->
                this.getString(getApplication(), R.string.error_not_regular_nickname)

            else -> ""
        }
    }

    fun signUp() {
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

    }

    /* '~를 입력하세요.' 에러 메세지를 가져오는 메서드 */
    private fun getStringErrorInputMsg(s: String) =
        s + this.getString(getApplication(), R.string.error_input)

    private fun isNotEmpty(list: List<*>): Boolean {
        list.forEach { item ->
            if (item.toString().isNullOrBlank())
                return false
        }

        return true
    }

}