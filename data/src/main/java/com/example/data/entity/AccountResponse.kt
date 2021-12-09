package com.example.data.entity

import com.google.gson.annotations.SerializedName


data class AccountResponse(
    val accountID: String,
    val money: Int,
    val userPhone: String,
    @SerializedName("nickname")
    val nickname: String?,
    val user: UserResponse
)
