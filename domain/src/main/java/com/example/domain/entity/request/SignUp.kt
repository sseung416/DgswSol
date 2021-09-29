package com.example.domain.entity.request

import android.provider.ContactsContract

data class SignUp (
    val id: String,
    val password: String,
    val phoneNumber: String,
    val residentRegistrationNum: String,
    val name: String,
    val nickname: String,
    val profileImg: String
)