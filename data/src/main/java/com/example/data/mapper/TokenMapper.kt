package com.example.data.mapper

import com.example.data.entity.TokenResponse
import com.example.domain.entity.response.Token

fun Token.toResponse(): TokenResponse =
    TokenResponse(this.token)

fun TokenResponse.toEntity(): Token =
    Token(this.token)