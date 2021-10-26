package com.example.a2ndproject.ui.viewmodel.fragment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.request.QuickPw
import com.example.domain.entity.response.Token
import com.example.domain.usecase.user.PostQuickLoginUseCase
import com.example.domain.usecase.user.PostQuickSignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class NumberPadPinViewModel @Inject constructor(
    private val postQuickSignUpUseCase: PostQuickSignUpUseCase,
    private val postQuickLoginUseCase: PostQuickLoginUseCase
): ViewModel() {

    private val _isFailure = MutableLiveData("")
    val isFailure = _isFailure

    private val _isSuccessSignUp = MutableLiveData("")
    val isSuccessSignUp = _isSuccessSignUp

    private val _isSuccessLogin = MutableLiveData<Token>()
    val isSuccessLogin = _isSuccessLogin

    fun quickSignUp(pw: String) {
        viewModelScope.launch {
            try {
                val msg = postQuickSignUpUseCase.buildUseCase(PostQuickSignUpUseCase.Params(QuickPw(pw)))
                _isSuccessSignUp.postValue(msg)
            } catch (e: Exception) {
                Log.d("quickSignUp", e.message.toString())
                _isFailure.postValue("fail")
            }
        }
    }

    fun quickLogin(pw: String) {
        try {
            viewModelScope.launch {
                val msg = postQuickLoginUseCase.buildUseCase(PostQuickLoginUseCase.Params(QuickPw(pw)))
                _isSuccessLogin.postValue(msg)
            }
        } catch (e: Exception) {
            Log.d("quickLogin", e.message.toString())
            _isFailure.postValue("fail")
        }
    }
}