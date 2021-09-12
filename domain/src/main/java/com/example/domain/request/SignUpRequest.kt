/**
 * 회원가입 시, 서버에 회원가입 정보를 보낼 때 사용하는 데이터 클래스
 *
 * @author 최승연
 * @date 2021-09-10
 * */
package com.example.domain.request

data class SignUpRequest(
    val id: String,
    val password: String,
    val name: String,
    val phoneNumber: String,
    val residentRegistrationNumber: String,
    val nickname: String,
    val profileImage: String
)
