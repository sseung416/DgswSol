package com.example.data.repository

import com.example.data.datasource.AccountDataSource
import com.example.domain.entity.request.CheckAccount
import com.example.domain.entity.request.CreateAccount
import com.example.domain.entity.response.Msg
import com.example.domain.repository.AccountRepository
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val accountDataSource: AccountDataSource
) : AccountRepository {

    override suspend fun postCheckAccount(checkAccount: CheckAccount): Msg {
        return accountDataSource.postCheckAccount(checkAccount)
    }

    override suspend fun postCreateAccount(createAccount: CreateAccount): Msg {
        return accountDataSource.postCreateAccount(createAccount)
    }

    override suspend fun getHold() {
        TODO("Not yet implemented")
    }
}