package com.example.domain.usecase.account

import com.example.domain.base.ParamsUseCase
import com.example.domain.entity.request.CheckAccount
import com.example.domain.entity.response.Msg
import com.example.domain.repository.AccountRepository
import javax.inject.Inject

class PostCheckAccountUseCase @Inject constructor(
    private val accountRepository: AccountRepository
) : ParamsUseCase<PostCheckAccountUseCase.Params, Msg>() {

    override suspend fun buildUseCase(params: Params): Msg {
        return accountRepository.postCheckAccount(params.checkAccount)
    }

    data class Params(
        val checkAccount: CheckAccount
    )
}