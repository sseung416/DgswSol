package com.example.domain.usecase.transfer

import com.example.domain.base.ParamsUseCase
import com.example.domain.entity.request.Transfer
import com.example.domain.entity.response.Msg
import com.example.domain.entity.response.TransferRes
import com.example.domain.repository.TransferRepository
import javax.inject.Inject

class PutTransferUseCase @Inject constructor(
    private val transferRepository: TransferRepository
): ParamsUseCase<PutTransferUseCase.Params, TransferRes?>() {

    override suspend fun buildUseCase(params: Params): TransferRes? {
        return transferRepository.postTransfer(params.transfer)
    }

    data class Params(
        val transfer: Transfer
    )
}