package com.example.domain.repository

interface AccountRepository {

    suspend fun postSignUp()

    suspend fun postLoginId()

    suspend fun postLoginPin()
}