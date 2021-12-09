package com.example.domain.entity.request

data class Transfer(
    val money: Int?,
    val sendAccountId: String?,
    val receiveAccountId: String?
)

//"{money:int,
//sendAccountId:string,
//receiveAccountId:string}"