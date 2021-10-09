package com.example.domain.usecase.user

import android.media.session.MediaSession
import com.example.domain.base.ParamsUseCase
import com.example.domain.entity.request.QuickPw
import com.example.domain.entity.response.Token
import com.example.domain.repository.UserRepository
import javax.inject.Inject

class PostQuickLoginUseCase @Inject constructor(
    private val userRepository: UserRepository
) : ParamsUseCase<PostQuickLoginUseCase.Params, Token>() {

    override suspend fun buildUseCase(params: Params): Token {
        return userRepository.postQuickLogin(params.quickPw)
    }

    data class Params(
        val quickPw: QuickPw
    )
}