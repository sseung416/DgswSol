package com.example.data.entity


data class AccountResponse(
    val accountID: String,
    val money: Int,
    val userPhone: String,
    val nickname: String,
    val user: UserResponse
)
