package com.example.domain.usecase.user

import com.example.domain.base.ParamsUseCase
import com.example.domain.entity.request.DuplicateNickname
import com.example.domain.repository.UserRepository
import javax.inject.Inject

class GetDuplicateNicknameCheckUseCase @Inject constructor(
    private val userRepository: UserRepository
) : ParamsUseCase<GetDuplicateNicknameCheckUseCase.Params, String>() {

    override suspend fun buildUseCase(params: Params): String {
        return userRepository.getDuplicateNicknameCheck(params.nickname)
    }

    data class Params(
        val nickname: String
    )
}