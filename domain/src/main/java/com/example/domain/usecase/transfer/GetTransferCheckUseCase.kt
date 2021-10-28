package com.example.domain.usecase.transfer

import com.example.domain.base.BaseUseCase
import com.example.domain.entity.response.Msg
import com.example.domain.repository.TransferRepository
import javax.inject.Inject

class GetTransferCheckUseCase @Inject constructor(
    private val transferRepository: TransferRepository
) : BaseUseCase<Msg>() {

    override suspend fun buildUseCase(): Msg {
        return transferRepository.getTransferCheck()
    }
}