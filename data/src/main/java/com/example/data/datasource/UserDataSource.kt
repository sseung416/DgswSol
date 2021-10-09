package com.example.data.datasource

import com.example.data.base.BaseDataSource
import com.example.data.entity.TokenResponse
import com.example.data.mapper.toEntity
import com.example.data.network.remote.UserRemote
import com.example.domain.entity.request.Login
import com.example.domain.entity.request.QuickPw
import com.example.domain.entity.response.Token
import javax.inject.Inject

class UserDataSource @Inject constructor(
    override val remote: UserRemote
) : BaseDataSource<UserRemote>() {

    suspend fun getDuplicateId(id: String): String {
        return remote.getDuplicateId(id)
    }

    suspend fun getDuplicateName(name: String): String {
        return remote.getDuplicateName(name)
    }

    suspend fun postQuickSignUp(quickPw: QuickPw): Token {
        return remote.postQuickSignUp(quickPw).toEntity()
    }

//    suspend fun postSignUp()

    suspend fun postLogin(login: Login): Token {
        return remote.postLogin(login).toEntity()
    }

    suspend fun postQuickLogin(quickPw: QuickPw): Token {
        return remote.postQuickSignUp(quickPw).toEntity()
    }
}