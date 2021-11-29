package com.example.a2ndproject.ui.view.data

// 같은 프래그먼트인데 각각 다른 기능을 하는 프래그먼트의 구별을 위해 type을 지정함
enum class FragmentType(val type: Int) {
    // 개인 정보 입력 받음
    IDENTITY_AUTH_CREATE(0),
    IDENTITY_AUTH_CONNECT(1),

    // 핀패드
    // 핀번호 회원가입
    PIN_QUICK_SIGN_UP(0),
    // 핀번호 로그인
    PIN_QUICK_SIGN_IN(1),
    // 계좌 생성
    PIN_CREATE_ACCOUNT_PW(2),
    // 통장 비밀번호 설정/확인
    PIN_ACCOUNT_PW(3),

    TAB_POS_CREATE_ACCOUNT(0),
    TAB_POS_CONNECT_ACCOUNT(1),
}