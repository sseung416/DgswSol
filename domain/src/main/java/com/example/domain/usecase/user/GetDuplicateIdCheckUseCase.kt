package com.example.domain.usecase.user

import com.example.domain.base.ParamsUseCase
import com.example.domain.entity.response.Msg
import com.example.domain.repository.UserRepository
import javax.inject.Inject

class GetDuplicateIdCheckUseCase @Inject constructor(
    private val userRepository: UserRepository
) : ParamsUseCase<GetDuplicateIdCheckUseCase.Params, Msg>() {

    override suspend fun buildUseCase(params: Params): Msg {
        return userRepository.getDuplicateIdCheck(params.id)
    }

    data class Params(
        val id: String
    )
}