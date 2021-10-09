package com.example.domain.entity.request

data class SignUp (
    val id: String,
    val pw: String,
//    val email: String,
//    val phoneNumber: String,
    val birth: String,
    val name: String,
    val nickname: String,
//    val profile: String
)