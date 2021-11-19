package com.example.domain.usecase.user

import com.example.domain.base.BaseUseCase
import com.example.domain.entity.response.Profile
import com.example.domain.repository.UserRepository
import javax.inject.Inject

class GetProfileUseCase @Inject constructor(
    private val userRepository: UserRepository
): BaseUseCase<Profile>() {

    override suspend fun buildUseCase(): Profile {
        return userRepository.getProfile()
    }
}