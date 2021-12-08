package com.example.domain.entity.response

data class Account(
    val accountID: String,
    val money: Int,
    val userPhone: String,
    val nickname: String,
    val user: User
)

