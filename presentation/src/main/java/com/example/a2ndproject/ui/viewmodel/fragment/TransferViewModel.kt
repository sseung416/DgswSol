package com.example.a2ndproject.ui.viewmodel.fragment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.request.Transfer
import com.example.domain.entity.response.Account
import com.example.domain.usecase.transfer.GetTransferCheckUseCase
import com.example.domain.usecase.transfer.PutTransferUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import javax.inject.Inject

@HiltViewModel
class TransferViewModel @Inject constructor(
    private val putTransferUseCase: PutTransferUseCase,
    private val getTransferCheckUseCase: GetTransferCheckUseCase
) : ViewModel() {

    val money = MutableLiveData<Int>()
    val targetAccount = MutableLiveData<String>()
    lateinit var fromAccount: String

    val isSuccess = MutableLiveData<String>()
    val isFailure = MutableLiveData<String>()

    val isSuccessCheckAccount = MutableLiveData<Account>()
    val isFailureCheckAccount = MutableLiveData<String>()

    fun postTransfer() {
        val transfer = Transfer(money.value!! + 300, fromAccount, targetAccount.value)

        try {
            viewModelScope.launch {
                val res = putTransferUseCase.buildUseCase(PutTransferUseCase.Params(transfer))

                Log.d("TransferViewModel", res.toString())
                when {
                    res == null -> isFailure.value = "fail"
                    res.status == 200 -> isSuccess.value = "success"
                }
            }
        } catch (e: Exception) {
            Log.e("TransferViewModel", e.message.toString())
            isFailure.value = e.message
        }
    }

    fun transferAccountCheck() {
        try {
            viewModelScope.launch {
                val res = getTransferCheckUseCase.buildUseCase(targetAccount.value!!)

                when  {
                    res == null -> isFailureCheckAccount.value = "fail"
                    res.status == 200 -> isSuccessCheckAccount.value = res.data!!
                }
            }
        } catch (e: Exception) {
            Log.d("transferAccountCheck", e.message.toString())
            isFailureCheckAccount.value = e.message.toString()
        }
    }
}