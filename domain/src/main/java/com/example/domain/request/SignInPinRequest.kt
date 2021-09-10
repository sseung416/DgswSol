package com.example.domain.request

data class SignInPinRequest(
    val pinNumber: String,
    val phoneNumber: String
)
