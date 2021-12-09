package com.example.data.datasource

import com.example.data.base.BaseDataSource
import com.example.data.base.BaseRemote
import com.example.data.mapper.toEntity
import com.example.data.network.remote.TransferRemote
import com.example.domain.entity.request.Transfer
import com.example.domain.entity.request.TransferPw
import com.example.domain.entity.response.Account
import com.example.domain.entity.response.Msg
import com.example.domain.entity.response.Res
import com.example.domain.entity.response.TransferRes
import javax.inject.Inject

class TransferDataSource @Inject constructor(
    override val remote: TransferRemote
) : BaseDataSource<TransferRemote>() {

    suspend fun getTransferCheck(receive: String): Res<Account>? {
        val res = remote.getTransferCheck(receive)

        return if (res != null) {
            remote.getTransferCheck(receive)!!.toEntity()
        } else {
            null
        }
    }

    suspend fun postTransfer(transfer: Transfer): TransferRes? {
        return remote.postTransfer(transfer)
    }

    suspend fun postTransferPw(transferPw: TransferPw): Msg {
        return remote.postTransferPw(transferPw).toEntity()
    }
}