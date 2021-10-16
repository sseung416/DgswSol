package com.example.a2ndproject.ui.view.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class BaseViewModelFactory<U>(
    val useCase: U
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(modelClass.javaClass)) {
            modelClass.getConstructor(modelClass.javaClass).newInstance(useCase)
        } else {
            throw IllegalAccessException("BaseViewModelFactory")
        }
    }
}