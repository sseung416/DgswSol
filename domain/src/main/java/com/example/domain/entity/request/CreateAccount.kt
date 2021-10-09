package com.example.domain.entity.request

data class CreateAccount(
    val name: String,
    val birth: String,
    val nickname: String,
    val pw: String
)
