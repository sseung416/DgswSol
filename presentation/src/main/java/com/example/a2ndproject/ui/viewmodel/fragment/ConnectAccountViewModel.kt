package com.example.a2ndproject.ui.viewmodel.fragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.a2ndproject.ui.view.model.Connect

class ConnectAccountViewModel : ViewModel() {

    val isSuccess = MutableLiveData<List<Connect>>()
    val isFailure = MutableLiveData<String>()

    fun getAccountList() {
        // todo 서버 완성되면 서버 통신 구현. . .
        val dummy = listOf(
            Connect("토스", "111-1-11-111", 1000),
            Connect("토스", "111-1-11-111", 1000),
            Connect("토스", "111-1-11-111", 1000),
            Connect("토스", "111-1-11-111", 1000),
            Connect("토스", "111-1-11-111", 1000),
        )

        isSuccess.value = dummy
    }
}