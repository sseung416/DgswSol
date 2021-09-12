/**
 * 아이디로 로그인 시 사용되는 유스케이스
 *
 * @author 최승연
 * @date 2021-09-10
 * */
package com.example.domain.usecase.account

import com.example.domain.base.ParamsUseCase
import com.example.domain.entity.Token
import com.example.domain.repository.AccountRepository
import com.example.domain.request.SignInIdRequest
import javax.inject.Inject

class PostSignInId @Inject constructor(
    private val accountRepository: AccountRepository
) : ParamsUseCase<PostSignInId.Params, Token>() {

    override suspend fun invoke(params: Params): Token {
        return accountRepository.postSignInId(params.signInIdRequest)
    }

    data class Params(
        val signInIdRequest: SignInIdRequest
    )
}