package com.example.data.datasource

import com.example.data.base.BaseDataSource
import com.example.data.base.BaseRemote
import com.example.data.mapper.toEntity
import com.example.data.network.remote.TransferRemote
import com.example.domain.entity.request.Transfer
import com.example.domain.entity.request.TransferPw
import com.example.domain.entity.response.Msg
import javax.inject.Inject

class TransferDataSource @Inject constructor(
    override val remote: TransferRemote
) : BaseDataSource<TransferRemote>() {

    suspend fun getTransferCheck(receive: String): Msg {
        return remote.getTransferCheck(receive).toEntity()
    }

    suspend fun postTransfer(transfer: Transfer): Msg {
        return remote.postTransfer(transfer).toEntity()
    }

    suspend fun postTransferPw(transferPw: TransferPw): Msg {
        return remote.postTransferPw(transferPw).toEntity()
    }
}