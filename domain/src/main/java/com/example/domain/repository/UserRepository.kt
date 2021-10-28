/**
 *
 * @author
 * @date
 * */
package com.example.domain.repository

import com.example.domain.entity.request.*
import com.example.domain.entity.response.Msg
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface UserRepository {

    // 아이디 중복 체크
    suspend fun getDuplicateIdCheck(id: String): Msg

    // 별명 중복 체크
    suspend fun getDuplicateNicknameCheck(nickname: String): Msg

    // 회원가입
    suspend fun postSignUp(
        id: RequestBody,
        pw: RequestBody,
        phonenum: RequestBody,
        birth: RequestBody,
        name: RequestBody,
        nickname: RequestBody,
        attachment: MultipartBody.Part
    ): Msg

    // 회원가입 - 간편 인증 번호
    suspend fun postQuickSignUp(quickPw: QuickPw): Msg

    // 일반 로그인
    suspend fun postLogin(login: Login): Msg

    // 간편 로그인
    suspend fun postQuickLogin(quickPw: QuickPw): Msg

}