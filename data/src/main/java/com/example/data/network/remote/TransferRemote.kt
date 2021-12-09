package com.example.data.network.remote

import com.example.data.base.BaseRemote
import com.example.data.entity.AccountResponse
import com.example.data.entity.MsgResponse
import com.example.data.network.service.TransferService
import com.example.domain.base.BaseUseCase
import com.example.domain.entity.request.Transfer
import com.example.domain.entity.request.TransferPw
import com.example.domain.entity.response.Res
import com.example.domain.entity.response.TransferRes
import javax.inject.Inject

class TransferRemote @Inject constructor(
    override val service: TransferService
) : BaseRemote<TransferService>() {

    suspend fun getTransferCheck(receive: String): Res<AccountResponse>? {
        return getResponse(service.getTransferCheck(receive))
    }

    suspend fun postTransfer(transfer: Transfer): TransferRes? {
        return getResponse(service.postTransfer(transfer))
    }

    suspend fun postTransferPw(transferPw: TransferPw): MsgResponse {
        return getResponse(service.postTransferPw(transferPw))!!
    }
}