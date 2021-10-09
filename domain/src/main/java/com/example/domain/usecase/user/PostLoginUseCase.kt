package com.example.domain.usecase.user

import com.example.domain.base.ParamsUseCase
import com.example.domain.entity.request.Login
import com.example.domain.entity.response.Token
import com.example.domain.repository.UserRepository
import javax.inject.Inject

class PostLoginUseCase @Inject constructor(
    private val userRepository: UserRepository
) : ParamsUseCase<PostLoginUseCase.Params, Token>() {

    override suspend fun buildUseCase(params: Params): Token {
        return userRepository.postLogin(params.login)
    }

    data class Params(
        val login: Login
    )
}