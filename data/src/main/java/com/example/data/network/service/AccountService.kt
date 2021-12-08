package com.example.data.network.service

import com.example.data.entity.AccountListResponse
import com.example.data.entity.AccountResponse
import com.example.data.entity.MsgResponse
import com.example.domain.entity.request.CheckAccount
import com.example.domain.entity.request.CreateAccount
import com.example.domain.entity.response.Account
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AccountService {

    @POST("/account/check")
    suspend fun postCheckAccount(@Body checkAccount: CheckAccount): Response<MsgResponse>

    @POST("/account")
    suspend fun postCreateAccount(@Body createAccount: CreateAccount): Response<MsgResponse>

    @GET("/account")
    suspend fun getHomeAccount(): Response<AccountListResponse>
}