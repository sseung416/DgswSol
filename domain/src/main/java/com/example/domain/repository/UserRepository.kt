/**
 *
 * @author
 * @date
 * */
package com.example.domain.repository

import com.example.domain.entity.request.*
import com.example.domain.entity.response.Response
import com.example.domain.entity.response.Token

interface UserRepository {

    // 아이디 중복 체크
    suspend fun getDuplicateIdCheck(duplicateId: DuplicateId): String

    // 별명 중복 체크
    suspend fun getDuplicateNicknameCheck(duplicateNickname: DuplicateNickname): String

    // 회원가입
    suspend fun postSignUp(signUp: SignUp): String

    // 회원가입 - 간편 인증 번호
    suspend fun postQuickSignUp(quickPw: QuickPw): Token

    // 일반 로그인
    suspend fun postLogin(login: Login): Token

    // 간편 로그인
    suspend fun postQuickLogin(quickPw: QuickPw): Token

}