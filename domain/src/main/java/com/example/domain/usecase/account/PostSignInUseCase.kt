package com.example.domain.usecase.account

import android.media.session.MediaSession
import com.example.domain.base.ParamsUseCase
import com.example.domain.entity.request.SignIn
import com.example.domain.entity.response.Response
import com.example.domain.entity.response.Token
import com.example.domain.repository.AccountRepository
import javax.inject.Inject

class PostSignInUseCase @Inject constructor(
    private val accountRepository: AccountRepository
) : ParamsUseCase<PostSignInUseCase.Params, Response<Token>>() {

    override suspend fun buildUseCase(params: Params): Response<Token> {
        return accountRepository.postSignIn(params.signIn)
    }

    data class Params (
        val signIn: SignIn
        )
}