package com.example.a2ndproject.ui.viewmodel.fragment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.response.Account
import com.example.domain.repository.AccountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class HomeTabViewModel @Inject constructor(
    private val accountRepository: AccountRepository
) : ViewModel() {

    val isSuccessGetHomeAccount = MutableLiveData<ArrayList<Account>>(arrayListOf())
    val isFailureGetHomeAccount = MutableLiveData<String>()

    fun getHomeAccount() {
        try {
            viewModelScope.launch {
                val res = accountRepository.getHomeAccount()

                Log.d("getHomeAccount", res.toString())
                when (res) {
                    null -> isFailureGetHomeAccount.value = "fail"
                    else -> isSuccessGetHomeAccount.value!!.addAll(res.accountList)
                }
            }
        } catch (e: Exception) {
            Log.e("getHomeAccount", e.message.toString())
            isFailureGetHomeAccount.value = e.message.toString()
        }
    }
}