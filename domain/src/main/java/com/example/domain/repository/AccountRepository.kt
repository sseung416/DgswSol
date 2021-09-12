/**
 * 계정 관련 레포지토리...
 *
 * @author 최승연
 * @date 2021-09-10
 * */
package com.example.domain.repository

import com.example.domain.entity.Token
import com.example.domain.request.SignInIdRequest
import com.example.domain.request.SignInPinRequest
import com.example.domain.request.SignUpRequest

interface AccountRepository {

    suspend fun postSignUp(signUpRequest: SignUpRequest): Token

    suspend fun postSignInId(signInIdRequest: SignInIdRequest): Token

    suspend fun postSignInPin(signInPinRequest: SignInPinRequest): Token
}