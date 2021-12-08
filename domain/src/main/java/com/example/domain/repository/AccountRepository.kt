package com.example.domain.repository

import com.example.domain.entity.request.CheckAccount
import com.example.domain.entity.request.CreateAccount
import com.example.domain.entity.response.Account
import com.example.domain.entity.response.AccountList
import com.example.domain.entity.response.Msg

interface AccountRepository {

    // 계좌 개인정보 확인
    suspend fun postCheckAccount(checkAccount: CheckAccount): Msg

    // 통장 개설
    suspend fun postCreateAccount(createAccount: CreateAccount): Msg

    // 메인 화면
    suspend fun getHomeAccount(): List<Account>

    // 본인의 계좌에서 돈 가져오기
    suspend fun getHold()
}