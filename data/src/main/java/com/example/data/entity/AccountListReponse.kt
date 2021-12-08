package com.example.data.entity

import com.google.gson.annotations.SerializedName

data class AccountListResponse(
    @SerializedName("data")
    val accountList: List<AccountResponse>
)
