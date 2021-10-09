package com.example.domain.repository

import com.example.domain.entity.request.CheckAccount
import com.example.domain.entity.request.CreateAccount

interface AccountRepository {

    // 계좌 개인정보 확인
    suspend fun postCheckAccount(checkAccount: CheckAccount): String

    // 통장 개설
    suspend fun postCreateAccount(createAccount: CreateAccount): String

    // 본인의 계좌에서 돈 가져오기
    suspend fun getHold()
}