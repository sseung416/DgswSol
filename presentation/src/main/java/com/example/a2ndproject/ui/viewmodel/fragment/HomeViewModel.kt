package com.example.a2ndproject.ui.viewmodel.fragment

import androidx.lifecycle.MutableLiveData
import com.example.a2ndproject.ui.view.base.BaseViewModel

class HomeViewModel : BaseViewModel() {

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