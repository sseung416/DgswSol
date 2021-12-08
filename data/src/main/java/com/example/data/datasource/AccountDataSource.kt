package com.example.data.datasource

import com.example.data.base.BaseDataSource
import com.example.data.entity.AccountResponse
import com.example.data.mapper.toEntity
import com.example.data.network.remote.AccountRemote
import com.example.domain.entity.request.CheckAccount
import com.example.domain.entity.request.CreateAccount
import com.example.domain.entity.response.Account
import com.example.domain.entity.response.AccountList
import com.example.domain.entity.response.Msg
import javax.inject.Inject

class AccountDataSource @Inject constructor(
    override val remote: AccountRemote
) : BaseDataSource<AccountRemote>() {

    suspend fun postCheckAccount(checkAccount: CheckAccount): Msg {
        return remote.postCheckAccount(checkAccount).toEntity()
    }

    suspend fun postCreateAccount(createAccount: CreateAccount): Msg {
        return remote.postCreateAccount(createAccount).toEntity()
    }

    suspend fun getHomeAccount(): List<Account> {
        return remote.getHomeAccount().toEntity()
    }
}