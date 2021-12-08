package com.example.domain.usecase.account

import com.example.domain.base.BaseUseCase
import com.example.domain.entity.response.Account
import com.example.domain.entity.response.AccountList
import com.example.domain.repository.AccountRepository
import javax.inject.Inject

class GetHomeAccountUseCase @Inject constructor(
    private val accountRepository: AccountRepository
) : BaseUseCase<AccountList>() {

    override suspend fun buildUseCase(): AccountList {
        return accountRepository.getHomeAccount()
    }
}