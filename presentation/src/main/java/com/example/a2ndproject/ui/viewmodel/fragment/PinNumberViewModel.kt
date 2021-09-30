package com.example.a2ndproject.ui.viewmodel.fragment

import androidx.lifecycle.ViewModel

class PinNumberViewModel : ViewModel() {

    fun isCorrectPin(): Boolean {
        return false
    }

    fun getErrorText(): String {
        return "오류~~~~~"
    }
}