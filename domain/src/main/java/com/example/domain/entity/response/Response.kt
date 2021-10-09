package com.example.domain.entity.response

data class Response<T>(
    val status: Int,
    val data: T
)
