package com.example.a2ndproject.ui.viewmodel.fragment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.request.CheckAccount
import com.example.domain.usecase.account.PostCheckAccountUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IdentityAuthViewModel @Inject constructor(
    private val postCheckAccountUseCase: PostCheckAccountUseCase
) : ViewModel() {

    val name = MutableLiveData<String>()
    val number = MutableLiveData<String>()
    val numberBack = MutableLiveData<String>()
    val error = MutableLiveData("")

    private val _isSuccessCheck = MutableLiveData<String>()
    val isSuccessCheck = _isSuccessCheck

    private val _isFailureCheck = MutableLiveData<String>()
    val isFailureCheck = _isFailureCheck

    fun isErrorBlank(): Boolean {
        return error.value == ""
    }

    fun postCheckAccount() {
        val name = name.value!!
        val number = number.value!!

        try {
            viewModelScope.launch {
                val msg = postCheckAccountUseCase.buildUseCase(PostCheckAccountUseCase.Params(CheckAccount(name, number)))
                Log.d("checkAccount", msg.toString())
                when (msg.msg) {
                    "exist" -> _isSuccessCheck.postValue(msg.msg!!)

                    else -> _isFailureCheck.postValue(msg.msg!!)
                }
            }
        } catch (e: Exception) {
            Log.d("checkAccount", e.message.toString())
            _isFailureCheck.postValue(e.message.toString())
        }
    }
}