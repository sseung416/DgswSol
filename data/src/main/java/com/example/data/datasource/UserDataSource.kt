package com.example.data.datasource

import com.example.data.base.BaseDataSource
import com.example.data.entity.MsgResponse
import com.example.data.mapper.toEntity
import com.example.data.network.remote.UserRemote
import com.example.domain.entity.request.Login
import com.example.domain.entity.request.QuickPw
import com.example.domain.entity.response.Msg
import com.example.domain.entity.response.Profile
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class UserDataSource @Inject constructor(
    override val remote: UserRemote
) : BaseDataSource<UserRemote>() {

    suspend fun getDuplicateIdCheck(id: String): Msg {
        return remote.getDuplicateIdCheck(id).toEntity()
    }

    suspend fun getDuplicateNameCheck(name: String): Msg {
        return remote.getDuplicateNameCheck(name).toEntity()
    }

    suspend fun postQuickSignUp(quickPw: QuickPw): Msg {
        return remote.postQuickSignUp(quickPw).toEntity()
    }

    suspend fun postSignUp(
        id: RequestBody,
        pw: RequestBody,
        phonenum: RequestBody,
        birth: RequestBody,
        name: RequestBody,
        nickname: RequestBody,
        attachment: MultipartBody.Part
    ): Msg {
        return remote.postSignUp(id, pw, phonenum, birth, name, nickname, attachment).toEntity()
    }

    suspend fun postLogin(login: Login): Msg {
        return remote.postLogin(login).toEntity()
    }

    suspend fun postQuickLogin(quickPw: QuickPw): Msg {
        return remote.postQuickLogin(quickPw).toEntity()
    }

    suspend fun getProfile(): Profile {
        return remote.getProfile().toEntity()
    }
}