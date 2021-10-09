package com.example.domain.usecase.user

import com.example.domain.base.ParamsUseCase
import com.example.domain.entity.request.QuickPw
import com.example.domain.entity.response.Token
import com.example.domain.repository.UserRepository
import javax.inject.Inject

class PostQuickSignUpUseCase @Inject constructor(
    private val userRepository: UserRepository
) : ParamsUseCase<PostQuickSignUpUseCase.Params, Token>() {

    override suspend fun buildUseCase(params: Params): Token {
        return userRepository.postQuickSignUp(params.quickPw)
    }

    data class Params(
        val quickPw: QuickPw
    )

}