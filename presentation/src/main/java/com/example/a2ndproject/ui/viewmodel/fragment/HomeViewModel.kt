package com.example.a2ndproject.ui.viewmodel.fragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.a2ndproject.ui.view.base.BaseViewModel

class HomeViewModel : ViewModel() {

    // home
    fun setTab(pos: Int) {
        position.value = pos
    }

    // tab
    val position = MutableLiveData(0)
    val navigateAddAccount = MutableLiveData(false)

    fun navigateAddAccount() {
        navigateAddAccount.postValue(true)
    }
}