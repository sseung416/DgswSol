/**
 * Sign Up ViewModel Class
 *
 * @author 최승연
 * @date 2021-09-07
 * */
package com.example.a2ndproject.view.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignUpViewModel : ViewModel() {

    var isDoubleCheckedId = false
    var isDoubleCheckedPassword = false

    /**
     * 아이디 정규식 검사 메서드
     *
     * @return String? : 에러 메세지를 반환(에러가 없을 시 null)
     * @param id : 아이디
     * */
    fun isValidId(id: String): String? {
        /* 영어, 숫자를 포함한 3~12글자의 정규식 */
        val reg = "^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z[0-9]]{3,12}".toRegex()

        if (id.isEmpty()) return "아이디를 입력해주세요."
        else if (!id.matches(reg)) return "영문자 및 숫자를 포함한 3~12글자를 입력해주세요."

        return null
    }

    /**
     * 비밀번호 정규식 검사 메서드
     *
     * @return String? : 에러 메세지를 반환(에러가 없을 시 null)
     * @param password : 비밀번호
     * */
    fun isValidPassword(password: String): String? {
        /* 영어, 숫자, 특수기후를 모두 포함한 8~12글자의 정규식 */
        val reg = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&])[A-Za-z[0-9]$@$!%*#?&]{8,12}$".toRegex()

        if (password.isBlank()) return "비밀번호를 입력해주세요."
        else if (!password.matches(reg)) return "영문자, 숫자, 특수기호를 모두 포함한 8~12글자를 입력해주세요."

        return null
    }

    /**
     * 닉네임 정규식 검사 메서드
     *
     * @return String? : 에러 메세지를 반환(에러가 없을 시 null)
     * @param nickName : 닉네임
     * */
    fun isValidNickName(nickName: String): String? {
        /* 영어, 한글, 숫자의 2글자 이상의 조합의 정규식 */
        // TODO 닉네임 한도 정하기
        val reg = "^[a-zA-Z가-힣[0-9]]{2,100}".toRegex()

        if (nickName.isBlank()) return "닉네임을 입력해주세요."
        else if (!nickName.matches(reg)) return "2글자 이상의 닉네임을 입력해주세요."

        return null
    }

    fun isDoubleChecked(): String? {
        if (isDoubleCheckedId && !isDoubleCheckedPassword) {
            return "비밀번호 중복 확인을 해주세요."
        } else if (!isDoubleCheckedId && isDoubleCheckedPassword) {
            return "아이디 중복 확인을 해주세요."
        }
        return null
    }

}