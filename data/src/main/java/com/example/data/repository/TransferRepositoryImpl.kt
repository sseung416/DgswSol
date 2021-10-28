package com.example.data.repository

import com.example.data.datasource.TransferDataSource
import com.example.domain.entity.request.Transfer
import com.example.domain.entity.request.TransferPw
import com.example.domain.entity.response.Msg
import com.example.domain.repository.TransferRepository
import javax.inject.Inject

class TransferRepositoryImpl @Inject constructor(
    private val transferDataSource: TransferDataSource
) : TransferRepository {

    override suspend fun getTransferCheck(): Msg {
        return transferDataSource.getTransferCheck()
    }

    override suspend fun postTransfer(transfer: Transfer): Msg {
        return transferDataSource.postTransfer(transfer)
    }

    override suspend fun postTransferPw(transferPw: TransferPw): Msg {
        return transferDataSource.postTransferPw(transferPw)
    }
}