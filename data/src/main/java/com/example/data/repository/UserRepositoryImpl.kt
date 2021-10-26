package com.example.data.repository

import com.example.data.datasource.UserDataSource
import com.example.domain.entity.request.*
import com.example.domain.entity.response.Token
import com.example.domain.repository.UserRepository
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDataSource: UserDataSource
) : UserRepository {

    override suspend fun getDuplicateIdCheck(id: String): String {
        return userDataSource.getDuplicateIdCheck(id)
    }

    override suspend fun getDuplicateNicknameCheck(nickname: String): String {
        return userDataSource.getDuplicateNameCheck(nickname)
    }

    override suspend fun postSignUp(
        id: RequestBody,
        pw: RequestBody,
        phonenum: RequestBody,
        birth: RequestBody,
        name: RequestBody,
        nickname: RequestBody,
        attachment: MultipartBody.Part
    ): String {
        return userDataSource.postSignUp(id, pw, phonenum, birth, name, nickname, attachment)
    }

    override suspend fun postQuickSignUp(quickPw: QuickPw): Token {
        return userDataSource.postQuickSignUp(quickPw)
    }

    override suspend fun postLogin(login: Login): Token {
        return userDataSource.postLogin(login)
    }

    override suspend fun postQuickLogin(quickPw: QuickPw): Token {
        return userDataSource.postQuickLogin(quickPw)
    }
}