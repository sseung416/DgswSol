package com.example.domain.usecase.user

import com.example.domain.base.ParamsUseCase
import com.example.domain.entity.request.DuplicateId
import com.example.domain.repository.UserRepository
import javax.inject.Inject

class GetDuplicateIdCheckUseCase @Inject constructor(
    private val userRepository: UserRepository
) : ParamsUseCase<GetDuplicateIdCheckUseCase.Params, String>() {

    override suspend fun buildUseCase(params: Params): String {
        return userRepository.getDuplicateIdCheck(params.duplicateId)
    }

    data class Params(
        val duplicateId: DuplicateId
    )
}