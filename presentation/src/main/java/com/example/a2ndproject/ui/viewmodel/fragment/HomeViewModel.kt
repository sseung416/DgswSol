package com.example.a2ndproject.ui.viewmodel.fragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    // home
    fun setTab(pos: Int) {
        position.value = pos
    }

    // tab
    val position = MutableLiveData(0)
}