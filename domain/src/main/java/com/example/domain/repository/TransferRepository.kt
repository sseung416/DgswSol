package com.example.domain.repository

import com.example.domain.entity.request.Transfer
import com.example.domain.entity.request.TransferPw

interface TransferRepository {

    // 송금 계좌 확인
    suspend fun getTransferCheck(): String

    // 송금
    suspend fun postTransfer(transfer: Transfer): String

    // 송금 비밀번호 확인
    suspend fun postTransferPw(transferPw: TransferPw): String
}