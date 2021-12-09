package com.example.domain.entity.response

data class Res<T>(
    val status: Int,
    val msg: String,
    val data: T?
)