package com.example.a2ndproject.ui.viewmodel.fragment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.request.Transfer
import com.example.domain.usecase.transfer.PutTransferUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransferViewModel @Inject constructor(
    private val putTransferUseCase: PutTransferUseCase
) : ViewModel() {
    val money = MutableLiveData<Int>()
    val targetAccount = MutableLiveData<String>()

    val isSuccess = MutableLiveData<String>()
    val isFailure = MutableLiveData<String>()

    fun putTransfer(fromAccount: String) {
        val transfer = Transfer(money.value, fromAccount, targetAccount.value)

        try {
            viewModelScope.launch {
                val msg = putTransferUseCase.buildUseCase(PutTransferUseCase.Params(transfer))
                Log.d("TransferViewModel", msg.msg.toString())

                when (msg.msg) {
                    "success" -> isSuccess.value = msg.msg!!
                    "fail" -> isFailure.value = msg.msg!!
                }
            }
        } catch (e: Exception) {
            Log.e("TransferViewModel", e.message.toString())
            isFailure.value = e.message
        }
    }
}