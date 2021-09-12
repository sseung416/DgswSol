/**
 * 로그인 시, 서버에 로그인 아이디/비밀번호 정보를 보낼 때 사용하는 데이터 클래스
 *
 * @author 최승연
 * @date 2021-09-10
 * */
package com.example.domain.request

data class SignInIdRequest(
    val id: String,
    val password: String
)
