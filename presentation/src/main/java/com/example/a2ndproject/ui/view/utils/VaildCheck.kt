package com.example.a2ndproject.ui.view.utils

// 파라미터로 전달받은 리스트의 모든 아이템의 공백 검사

fun List<String?>.isNotBlankAll(): Boolean {
    this.forEach { item ->
        if (item.isNullOrEmpty())
            return false
    }

    return true
}

fun List<String?>.isBlankAll(): Boolean {
    this.forEach { item ->
        if (item!!.isNotBlank())
            return false
    }

    return true
}