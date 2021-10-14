package com.example.a2ndproject.ui.viewmodel.fragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class IdentityAuthViewModel : ViewModel() {
    val name = MutableLiveData<String>()
    val number = MutableLiveData<String>()
    val numberBack = MutableLiveData<String>()
    val btnEnabled = MutableLiveData(false)
    val error = MutableLiveData("")

    val navigateCheckInfo = MutableLiveData(false)

    fun isErrorBlank(): Boolean {
        return error.value == ""
    }

    fun navigateCheckInfo() {
        viewModelScope.launch {
            // todo ~
        }

        navigateCheckInfo.postValue(true)
    }
}