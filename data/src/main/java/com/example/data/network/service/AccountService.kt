package com.example.data.network.service

import com.example.data.entity.MsgResponse
import com.example.domain.entity.request.CheckAccount
import com.example.domain.entity.request.CreateAccount
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AccountService {

    @POST("/account/check")
    suspend fun postCheckAccount(@Body checkAccount: CheckAccount): Response<MsgResponse>

    @POST("/account/create")
    suspend fun postCreateAccount(@Body createAccount: CreateAccount): Response<MsgResponse>
}