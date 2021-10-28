package com.example.data.mapper

import com.example.data.entity.MsgResponse
import com.example.domain.entity.response.Msg

fun Msg.toResponse(): MsgResponse =
    MsgResponse(this.msg)

fun MsgResponse.toEntity(): Msg =
    Msg(this.msg)