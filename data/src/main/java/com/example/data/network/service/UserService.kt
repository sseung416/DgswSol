package com.example.data.network.service

import com.example.data.entity.MsgResponse
import com.example.data.entity.ProfileResponse
import com.example.domain.entity.request.Login
import com.example.domain.entity.request.QuickPw
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface UserService {

    @GET("/signup/id/{id}")
    suspend fun getDuplicateIdCheck(@Path("id") id: String): Response<MsgResponse>

    @GET("/signup/name/{name}")
    suspend fun getDuplicateNameCheck(@Path("name") name: String): Response<MsgResponse>

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
    ): Response<MsgResponse>

    @POST("/signup/quick")
    suspend fun postQuickSignUp(@Body quickPw: QuickPw): Response<MsgResponse>

    @POST("/login")
    suspend fun postLogin(@Body login: Login): Response<MsgResponse>

    @POST("/login/quick")
    suspend fun postQuickLogin(@Body quickPw: QuickPw): Response<MsgResponse>

    @GET("/signup/profile")
    suspend fun getProfile(): Response<ProfileResponse>
}