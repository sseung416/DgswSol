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
    //todo 다른 계좌 가져오기가 들어올 예정
) : ViewModel() {
    val name = MutableLiveData<String>()
    val number = MutableLiveData<String>()
    val numberBack = MutableLiveData<String>()
    val btnEnabled = MutableLiveData(false)
    val error = MutableLiveData("")

    val navigateCheckInfo = MutableLiveData(false)

    private val _isSuccessCheckAccount = MutableLiveData("")
    val isSuccessCheckAccount = _isSuccessCheckAccount

    private val _isFailure = MutableLiveData("")
    val isFailure = _isFailure

    fun isErrorBlank(): Boolean {
        return error.value == ""
    }

    fun navigateCheckInfo() {
        // todo 예외처..리...
        val name = name.value!!
        val number = number.value!! + numberBack.value!!

        try {
            viewModelScope.launch {
                val msg = postCheckAccountUseCase.buildUseCase(PostCheckAccountUseCase.Params(CheckAccount(name, number)))
                _isSuccessCheckAccount.postValue(msg)
            }
        } catch (e: Exception) {
            Log.d("checkAccount", e.message.toString())
            _isFailure.postValue(e.message.toString())
        }

        navigateCheckInfo.postValue(true)
    }
}