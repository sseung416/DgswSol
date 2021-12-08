package com.example.data.mapper

import com.example.data.entity.AccountListResponse
import com.example.data.entity.AccountResponse
import com.example.data.entity.UserResponse
import com.example.domain.entity.response.Account
import com.example.domain.entity.response.AccountList
import com.example.domain.entity.response.User

fun AccountListResponse.toEntity(): AccountList =
    AccountList(this.accountList.map {
        Account(
            it.accountID,
            it.money,
            it.userPhone,
            it.nickname,
            it.user.toEntity()
        )
    })

fun UserResponse.toEntity(): User =
    User(
        this.phone,
        this.name,
        this.id,
        this.birth
    )
