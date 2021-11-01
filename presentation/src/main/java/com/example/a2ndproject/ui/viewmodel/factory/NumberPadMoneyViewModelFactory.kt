package com.example.a2ndproject.ui.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.a2ndproject.ui.view.model.Account
import com.example.a2ndproject.ui.viewmodel.fragment.NumberPadMoneyViewModel

class NumberPadMoneyViewModelFactory(
    val account: Account
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(NumberPadMoneyViewModel::class.java)) {
            NumberPadMoneyViewModel(account) as T
        } else {
            throw IllegalAccessException("ViewModelFactory")
        }
    }
}