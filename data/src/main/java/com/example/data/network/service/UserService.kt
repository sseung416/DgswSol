package com.example.data.network.service

import com.example.data.entity.TokenResponse
import com.example.domain.entity.request.Login
import com.example.domain.entity.request.QuickPw
import com.example.domain.entity.response.Token
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface UserService {

    @GET("/signup/id/{id}")
    suspend fun getDuplicateIdCheck(@Path("id") id: String): Response<String>

    @GET("/signup/name/{name}")
    suspend fun getDuplicateNameCheck(@Path("name") name: String): Response<String?>

    @Multipart
    @POST("/signup")
    suspend fun postSignUp(
        @Part("id") id: RequestBody,
        @Part("pw") pw: RequestBody,
        @Part("phonenum") phonenum: RequestBody,
        @Part("birth") birth: RequestBody,
        @Part("name") name: RequestBody,
        @Part("nickname") nickname: RequestBody,
        @Part attachment: MultipartBody.Part
    ): Response<TokenResponse>

    @POST("/signup/quick")
    suspend fun postQuickSignUp(@Body quickPw: QuickPw): Response<TokenResponse>

    @POST("/login")
    suspend fun postLogin(@Body login: Login): Response<TokenResponse>

    @POST("/login/quick")
    suspend fun postQuickLogin(@Body quickPw: QuickPw): Response<TokenResponse>
}