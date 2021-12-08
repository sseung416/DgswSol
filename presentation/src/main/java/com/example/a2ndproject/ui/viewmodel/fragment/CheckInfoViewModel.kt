package com.example.a2ndproject.ui.viewmodel.fragment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.response.Profile
import com.example.domain.usecase.user.GetProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class CheckInfoViewModel @Inject constructor(
    private val getProfileUseCase: GetProfileUseCase
) : ViewModel() {
    val accountName = MutableLiveData<String>()

    val isSuccess = MutableLiveData<Profile>()
    val isFailure = MutableLiveData<String>()

    fun getProfile() {
        try {
            viewModelScope.launch {
                isSuccess.postValue(getProfileUseCase.buildUseCase())
            }
        } catch (e: Exception) {
            Log.d("getProfile", e.message.toString())
            isFailure.postValue(e.message.toString())
        }

    }
}