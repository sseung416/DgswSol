package com.example.a2ndproject.ui.view.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.data.entity.TokenResponse

abstract class BaseViewModel<U, R> : ViewModel() {
    abstract val useCase: U

    protected val _isSuccess = MutableLiveData<R>()
    val isSuccess = _isSuccess

    protected val _isFailure = MutableLiveData<R>()
    val isFailure = _isFailure
}