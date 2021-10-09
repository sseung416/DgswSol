package com.example.domain.usecase.transfer

import com.example.domain.base.BaseUseCase
import com.example.domain.repository.TransferRepository
import javax.inject.Inject

class GetTransferCheckUseCase @Inject constructor(
    private val transferRepository: TransferRepository
) : BaseUseCase<String>() {

    override suspend fun buildUseCase(): String {
        return transferRepository.getTransferCheck()
    }
}