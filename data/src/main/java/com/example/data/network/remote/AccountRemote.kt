package com.example.data.network.remote

import com.example.data.base.BaseRemote
import com.example.data.entity.MsgResponse
import com.example.data.network.service.AccountService
import com.example.domain.entity.request.CheckAccount
import com.example.domain.entity.request.CreateAccount
import javax.inject.Inject

class AccountRemote @Inject constructor(
    override val service: AccountService
) : BaseRemote<AccountService>() {

    suspend fun postCheckAccount(checkAccount: CheckAccount): MsgResponse {
        return getResponse(service.postCheckAccount(checkAccount))
    }

    suspend fun postCreateAccount(createAccount: CreateAccount): MsgResponse {
        return getResponse(service.postCreateAccount(createAccount))
    }
}