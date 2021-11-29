package com.example.a2ndproject.ui.view.model

/**
 * 서버에서 계좌 추가 관련 api 문서가 없어서
 * 임의로 작성하는 데이터 클래스
 * 추가되면 삭제 예정
 * */
data class Connect(
    val bank: String,
    val account: String,
    val money: Int
)
