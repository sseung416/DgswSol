package com.example.domain.entity

data class User(
    val id: String,
    val password: String,
    val name: String,
    val phoneNumber: String,
    val residentRegistrationNumber: String,
    val nickname: String,
    val profileImage: String
)
