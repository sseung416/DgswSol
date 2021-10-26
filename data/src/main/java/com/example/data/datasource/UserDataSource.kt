package com.example.data.datasource

import com.example.data.base.BaseDataSource
import com.example.data.mapper.toEntity
import com.example.data.network.remote.UserRemote
import com.example.domain.entity.request.Login
import com.example.domain.entity.request.QuickPw
import com.example.domain.entity.response.Token
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class UserDataSource @Inject constructor(
    override val remote: UserRemote
) : BaseDataSource<UserRemote>() {

    suspend fun getDuplicateIdCheck(id: String): String {
        return remote.getDuplicateIdCheck(id)
    }

    suspend fun getDuplicateNameCheck(name: String): String {
        return remote.getDuplicateNameCheck(name)
    }

    suspend fun postQuickSignUp(quickPw: QuickPw): String {
        return remote.postQuickSignUp(quickPw)
    }

    suspend fun postSignUp(
        id: RequestBody,
        pw: RequestBody,
        phonenum: RequestBody,
        birth: RequestBody,
        name: RequestBody,
        nickname: RequestBody,
        attachment: MultipartBody.Part
    ): Token {
        return remote.postSignUp(id, pw, phonenum, birth, name, nickname, attachment).toEntity()
    }

    suspend fun postLogin(login: Login): Token {
        return remote.postLogin(login).toEntity()
    }

    suspend fun postQuickLogin(quickPw: QuickPw): Token {
        return remote.postQuickLogin(quickPw).toEntity()
    }
}