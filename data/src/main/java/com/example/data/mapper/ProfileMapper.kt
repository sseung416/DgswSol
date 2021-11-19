package com.example.data.mapper

import com.example.data.entity.ProfileResponse
import com.example.domain.entity.response.Profile

fun Profile.toResponse(): ProfileResponse =
    ProfileResponse(
        this.phonenum,
        this.birth,
        this.name,
        this.nickname
    )

fun ProfileResponse.toEntity(): Profile =
    Profile(
        this.phonenum,
        this.birth,
        this.name,
        this.nickname
    )