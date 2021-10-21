package com.example.a2ndproject.ui.view.base

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class AndroidViewModelFactory<U>(
    private val application: Application,
    private val useCase: U
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(modelClass.javaClass)) {
            modelClass.getConstructor(modelClass.javaClass).newInstance(application, useCase)
        } else {
            throw IllegalAccessException(modelClass.name + " (AndroidViewModelFactory)")
        }
    }
}