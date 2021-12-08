package com.example.a2ndproject.ui.viewmodel.fragment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.request.Transfer
import com.example.domain.entity.response.Account
import com.example.domain.repository.AccountRepository
import com.example.domain.repository.TransferRepository
import com.example.domain.usecase.transfer.PostTransferPwUseCase
import com.example.domain.usecase.transfer.PutTransferUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HoldViewModel @Inject constructor(
    private val transferRepository: TransferRepository,
    private val accountRepository: AccountRepository
) : ViewModel() {

    val isSuccess = MutableLiveData<String>()
    val isFailure = MutableLiveData<String>()

    val isSuccessAccount = MutableLiveData<ArrayList<Account>>()
    val isFailureAccount = MutableLiveData<String>()

    fun hold(money: Int, from: String, target: String) {
        try {
            viewModelScope.launch {
                val transfer = Transfer(money, from, target)
                val res = transferRepository.postTransfer(transfer)

                when (res.msg) {
                    "success" -> isSuccess.value = res.msg!!
                    "fail" -> isFailure.value = res.msg!!
                }
            }
        } catch (e: Exception) {
            Log.e("", e.message.toString())
            isFailure.value = e.message.toString()
        }
    }

    fun getAccountList() {
        try {
            viewModelScope.launch {
                when (val res = accountRepository.getHomeAccount()) {
                    null -> isFailure.value = ""
                    else -> isSuccessAccount.value?.addAll(res.accountList)
                }
            }
        } catch (e: Exception) {
            Log.e("getAccountList", e.message.toString())
            isFailureAccount.value = e.message.toString()
        }
    }
}