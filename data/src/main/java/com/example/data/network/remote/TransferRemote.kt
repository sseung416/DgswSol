package com.example.data.network.remote

import com.example.data.base.BaseRemote
import com.example.data.entity.MsgResponse
import com.example.data.network.service.TransferService
import com.example.domain.base.BaseUseCase
import com.example.domain.entity.request.Transfer
import com.example.domain.entity.request.TransferPw
import javax.inject.Inject

class TransferRemote @Inject constructor(
    override val service: TransferService
) : BaseRemote<TransferService>() {

    suspend fun getTransferCheck(): MsgResponse {
        return getResponse(service.getTransferCheck())
    }

    suspend fun postTransfer(transfer: Transfer): MsgResponse {
        return getResponse(service.postTransfer(transfer))
    }

    suspend fun postTransferPw(transferPw: TransferPw): MsgResponse {
        return getResponse(service.postTransferPw(transferPw))
    }
}