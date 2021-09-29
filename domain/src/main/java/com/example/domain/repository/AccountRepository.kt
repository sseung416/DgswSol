/**
 *
 * @author
 * @date
 * */
package com.example.domain.repository

import com.example.domain.entity.request.SignIn
import com.example.domain.entity.request.SignUp
import com.example.domain.entity.response.Response
import com.example.domain.entity.response.Token

interface AccountRepository {
    suspend fun postSignUp(signUp: SignUp): Response<Token>

    suspend fun postSignIn(signIn: SignIn): Response<Token>
}