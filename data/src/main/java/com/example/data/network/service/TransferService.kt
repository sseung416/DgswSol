package com.example.data.network.service

import com.example.data.entity.MsgResponse
import com.example.domain.entity.request.Transfer
import com.example.domain.entity.request.TransferPw
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface TransferService {

    // 송금 계좌 확인
    @GET("/send/check/{receive}")
    suspend fun getTransferCheck(): Response<MsgResponse>

    // 송금
    @POST("/send")
    suspend fun postTransfer(@Body transfer: Transfer): Response<MsgResponse>

    // 송금 비밀번호 확인
    @POST("/send/pw")
    suspend fun postTransferPw(@Body transferPw: TransferPw): Response<MsgResponse>
}