package com.example.domain.usecase.account

import com.example.domain.base.ParamsUseCase
import com.example.domain.entity.request.CreateAccount
import com.example.domain.entity.response.Msg
import com.example.domain.repository.AccountRepository
import javax.inject.Inject

class PostCreateAccountUseCase @Inject constructor(
    private val accountRepository: AccountRepository
) : ParamsUseCase<PostCreateAccountUseCase.Params, Msg>() {

    override suspend fun buildUseCase(params: Params): Msg {
        return accountRepository.postCreateAccount(params.createAccount)
    }

    data class Params(
        val createAccount: CreateAccount
    )
}