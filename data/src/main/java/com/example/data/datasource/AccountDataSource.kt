package com.example.data.datasource

import com.example.data.base.BaseDataSource
import com.example.data.network.remote.AccountRemote
import com.example.domain.entity.request.CheckAccount
import com.example.domain.entity.request.CreateAccount
import javax.inject.Inject

class AccountDataSource @Inject constructor(
    override val remote: AccountRemote
) : BaseDataSource<AccountRemote>() {

    suspend fun postCheckAccount(checkAccount: CheckAccount): String {
        return remote.postCheckAccount(checkAccount)
    }

    suspend fun postCreateAccount(createAccount: CreateAccount): String {
        return remote.postCreateAccount(createAccount)
    }
}