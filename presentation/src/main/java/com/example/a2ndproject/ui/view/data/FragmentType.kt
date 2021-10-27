package com.example.a2ndproject.ui.view.data

// 같은 프래그먼트인데 각각 다른 기능을 하는 프래그먼트의 구별을 위해 type을 지정함
enum class FragmentType(val type: Int) {
    // 개인 정보 입력 받음
    IDENTITY_AUTH_CREATE(0),
    IDENTITY_AUTH_CONNECT(1),

    // 핀패드
    PIN_QUICK_SIGN_UP(0),
    PIN_QUICK_SIGN_IN(1),
    PIN_ACCOUNT_PW(2)
}