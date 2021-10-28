package com.example.domain.usecase.transfer

import com.example.domain.base.ParamsUseCase
import com.example.domain.entity.request.TransferPw
import com.example.domain.entity.response.Msg
import com.example.domain.repository.TransferRepository
import javax.inject.Inject

class PostTransferPwUseCase @Inject constructor(
    private val transferRepository: TransferRepository
): ParamsUseCase<PostTransferPwUseCase.Params, Msg>() {

    override suspend fun buildUseCase(params: Params): Msg {
        return transferRepository.postTransferPw(params.transferPw)
    }

    data class Params(
        val transferPw: TransferPw
    )
}