package com.example.domain.usecase.user

import com.example.domain.base.ParamsUseCase
import com.example.domain.entity.response.Msg
import com.example.domain.repository.UserRepository
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class PostSignUpUseCase @Inject constructor(
    private val userRepository: UserRepository
) : ParamsUseCase<PostSignUpUseCase.Params, Msg>() {

    override suspend fun buildUseCase(params: Params): Msg {
        return userRepository.postSignUp(
            params.id,
            params.pw,
            params.phonenum,
            params.birth,
            params.name,
            params.nickname,
            params.attachment
        )
    }

    data class Params(
        val id: RequestBody,
        val pw: RequestBody,
        val phonenum: RequestBody,
        val birth: RequestBody,
        val name: RequestBody,
        val nickname: RequestBody,
        val attachment: MultipartBody.Part
    )
}