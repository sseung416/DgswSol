package com.example.a2ndproject.view.viewmodel

import androidx.lifecycle.ViewModel

class PinNumberViewModel : ViewModel() {

    fun isCorrectPin(): Boolean {
        return false
    }

    fun getErrorText(): String {
        return "오류~~~~~"
    }
}