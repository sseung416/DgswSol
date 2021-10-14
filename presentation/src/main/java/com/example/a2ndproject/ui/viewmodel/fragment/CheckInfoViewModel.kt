package com.example.a2ndproject.ui.viewmodel.fragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class CheckInfoViewModel : ViewModel() {
    val accountName = MutableLiveData<String>()

    val navigateToPin = MutableLiveData(false)

    fun navigateToPin() {
        viewModelScope.launch {
            // todo ~ ~ ~
        }

        navigateToPin.postValue(true)
    }
}