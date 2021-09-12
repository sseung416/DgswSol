/**
 * 회원가입 시 사용되는 유스케이스
 *
 * @author 최승연
 * @date 2021-09-10
 * */
package com.example.domain.usecase.account

import com.example.domain.base.ParamsUseCase
import com.example.domain.entity.Token
import com.example.domain.repository.AccountRepository
import com.example.domain.request.SignUpRequest
import javax.inject.Inject

class PostSignUp @Inject constructor(
    private val accountRepository: AccountRepository
) : ParamsUseCase<PostSignUp.Params, Token>() {

    override suspend fun invoke(params: Params): Token {
        return accountRepository.postSignUp(params.signUpRequest)
    }

    data class Params (
        val signUpRequest: SignUpRequest
    )
}