package com.example.data.network.service

import com.example.data.entity.TokenResponse
import com.example.domain.entity.request.Login
import com.example.domain.entity.request.QuickPw
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserService {

    @GET("/signup/id/{id}")
    suspend fun getDuplicateId(@Path("id") id: String): Response<String?>

    @GET("/signup/name/{name}")
    suspend fun getDuplicateName(@Path("name") name: String): Response<String?>

    // todo 아 추워요
    @POST("/signup")
    fun postSignUp()

    @POST("/signup/quick")
    suspend fun postQuickSignUp(@Body quickPw: QuickPw): Response<TokenResponse>

    @POST("/login")
    suspend fun postLogin(@Body login: Login): Response<TokenResponse>

    @POST("/login/quick")
    suspend fun postQuickLogin(@Body quickPw: QuickPw): Response<TokenResponse>
}