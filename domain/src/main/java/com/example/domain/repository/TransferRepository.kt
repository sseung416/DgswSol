package com.example.domain.repository

import com.example.domain.entity.request.Transfer
import com.example.domain.entity.request.TransferPw
import com.example.domain.entity.response.Msg

interface TransferRepository {

    // 송금 계좌 확인
    suspend fun getTransferCheck(targetAccount: String): Msg

    // 송금
    suspend fun postTransfer(transfer: Transfer): Msg

    // 송금 비밀번호 확인
    suspend fun postTransferPw(transferPw: TransferPw): Msg
}