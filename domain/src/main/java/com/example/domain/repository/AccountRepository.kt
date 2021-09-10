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