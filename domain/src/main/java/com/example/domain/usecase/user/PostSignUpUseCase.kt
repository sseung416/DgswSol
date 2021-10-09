package com.example.domain.usecase.user

import com.example.domain.base.ParamsUseCase
import com.example.domain.entity.request.SignUp
import com.example.domain.repository.UserRepository
import javax.inject.Inject

class PostSignUpUseCase @Inject constructor(
    private val userRepository: UserRepository
) : ParamsUseCase<PostSignUpUseCase.Params, String>() {

    override suspend fun buildUseCase(params: Params): String {
        return userRepository.postSignUp(params.signUp)
    }

    data class Params(
        val signUp: SignUp
    )
}