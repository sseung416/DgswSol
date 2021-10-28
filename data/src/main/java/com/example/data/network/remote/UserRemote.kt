package com.example.data.network.remote

import com.example.data.base.BaseRemote
import com.example.data.entity.MsgResponse
import com.example.data.network.service.UserService
import com.example.domain.entity.request.Login
import com.example.domain.entity.request.QuickPw
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class UserRemote @Inject constructor(
    override val service: UserService
) : BaseRemote<UserService>() {

    suspend fun getDuplicateIdCheck(id: String): MsgResponse {
        return getResponse(service.getDuplicateIdCheck(id))
    }

    suspend fun getDuplicateNameCheck(name: String): MsgResponse {
        return getResponse(service.getDuplicateNameCheck(name))
    }

    suspend fun postQuickSignUp(quickPw: QuickPw): MsgResponse {
        return getResponse(service.postQuickSignUp(quickPw))
    }

    suspend fun postSignUp(
        id: RequestBody,
        pw: RequestBody,
        phonenum: RequestBody,
        birth: RequestBody,
        name: RequestBody,
        nickname: RequestBody,
        attachment: MultipartBody.Part
    ): MsgResponse {
        return getResponse(service.postSignUp(id, pw, phonenum, birth, name, nickname, attachment))
    }

    suspend fun postLogin(login: Login): MsgResponse {
        return getResponse(service.postLogin(login))
    }

    suspend fun postQuickLogin(quickPw: QuickPw): MsgResponse {
        return getResponse(service.postQuickLogin(quickPw))
    }

}