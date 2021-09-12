/**
 * User 데이터 클래스..
 *
 * @author 최승연
 * @date 2021-09-10
 * */
package com.example.domain.entity

data class User(
    val name: String,
    val phoneNumber: String,
    val residentRegistrationNumber: String,
    val nickname: String,
    val profileImage: String
)
