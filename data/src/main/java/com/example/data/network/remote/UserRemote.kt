package com.example.data.network.remote

import com.example.data.base.BaseRemote
import com.example.data.entity.TokenResponse
import com.example.data.network.service.UserService
import com.example.domain.entity.request.Login
import com.example.domain.entity.request.QuickPw
import javax.inject.Inject

class UserRemote @Inject constructor(
    override val service: UserService
) : BaseRemote<UserService>() {

    suspend fun getDuplicateId(id: String): String {
        return getResponse(service.getDuplicateId(id))!!
    }

    suspend fun getDuplicateName(name: String): String {
        return getResponse(service.getDuplicateName(name))!!
    }

    suspend fun postQuickSignUp(quickPw: QuickPw): TokenResponse {
        return getResponse(service.postQuickSignUp(quickPw))
    }

//    suspend fun postSignUp()

    suspend fun postLogin(login: Login): TokenResponse {
        return getResponse(service.postLogin(login))
    }

    suspend fun postQuickLogin(quickPw: QuickPw): TokenResponse {
        return getResponse(service.postQuickLogin(quickPw))
    }

}