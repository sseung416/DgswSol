package com.example.domain.usecase.transfer

import com.example.domain.base.ParamsUseCase
import com.example.domain.entity.request.Transfer
import com.example.domain.repository.TransferRepository
import javax.inject.Inject

class PostTransferUseCase @Inject constructor(
    private val transferRepository: TransferRepository
): ParamsUseCase<PostTransferUseCase.Params, String>() {

    override suspend fun buildUseCase(params: Params): String {
        return transferRepository.postTransfer(params.transfer)
    }

    data class Params(
        val transfer: Transfer
    )
}