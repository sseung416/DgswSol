package com.example.domain.entity.request

data class Transfer(
    val money: Int?,
    val fromaccount: String?,
    val targetaccount: String?
)
