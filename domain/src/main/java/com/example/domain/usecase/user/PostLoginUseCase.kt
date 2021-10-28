package com.example.domain.usecase.user

import com.example.domain.base.ParamsUseCase
import com.example.domain.entity.request.Login
import com.example.domain.entity.response.Msg
import com.example.domain.repository.UserRepository
import javax.inject.Inject

class PostLoginUseCase @Inject constructor(
    private val userRepository: UserRepository
) : ParamsUseCase<PostLoginUseCase.Params, Msg>() {

    override suspend fun buildUseCase(params: Params): Msg {
        return userRepository.postLogin(params.login)
    }

    data class Params(
        val login: Login
    )
}