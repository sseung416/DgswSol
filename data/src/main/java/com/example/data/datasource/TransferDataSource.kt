package com.example.data.datasource

import com.example.data.base.BaseDataSource
import com.example.data.base.BaseRemote
import com.example.data.network.remote.TransferRemote
import com.example.domain.entity.request.Transfer
import com.example.domain.entity.request.TransferPw
import javax.inject.Inject

class TransferDataSource @Inject constructor(
    override val remote: TransferRemote
) : BaseDataSource<TransferRemote>() {

    suspend fun getTransferCheck(): String {
        return remote.getTransferCheck()
    }

    suspend fun postTransfer(transfer: Transfer): String {
        return remote.postTransfer(transfer)
    }

    suspend fun postTransferPw(transferPw: TransferPw): String {
        return remote.postTransferPw(transferPw)
    }
}