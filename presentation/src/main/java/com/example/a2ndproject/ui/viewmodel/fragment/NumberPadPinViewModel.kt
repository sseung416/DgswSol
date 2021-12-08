package com.example.a2ndproject.ui.viewmodel.fragment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.request.CreateAccount
import com.example.domain.entity.request.QuickPw
import com.example.domain.entity.response.Msg
import com.example.domain.usecase.account.PostCreateAccountUseCase
import com.example.domain.usecase.user.PostQuickLoginUseCase
import com.example.domain.usecase.user.PostQuickSignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class NumberPadPinViewModel @Inject constructor(
    private val postQuickSignUpUseCase: PostQuickSignUpUseCase,
    private val postQuickLoginUseCase: PostQuickLoginUseCase,
    private val postCreateAccountUseCase: PostCreateAccountUseCase
): ViewModel() {

    private val _isFailure = MutableLiveData<String>()
    val isFailure = _isFailure

    private val _isSuccessSignUp = MutableLiveData<String>()
    val isSuccessSignUp = _isSuccessSignUp

    private val _isSuccessLogin = MutableLiveData<String>()
    val isSuccessLogin = _isSuccessLogin

    private val _isSuccessCreate = MutableLiveData<String>()
    val isSuccessCreate = _isSuccessCreate

    fun quickSignUp(pw: String) {
        viewModelScope.launch {
            try {
                val msg = postQuickSignUpUseCase.buildUseCase(PostQuickSignUpUseCase.Params(QuickPw(pw)))

                when (msg.msg) {
                    "success" -> _isSuccessSignUp.value = msg.msg!!
                    "fail" -> _isFailure.value = msg.msg!!
                }
            } catch (e: Exception) {
                Log.d("quickSignUp", e.message.toString())
                _isFailure.postValue(e.message.toString())
            }
        }
    }

    fun quickLogin(pw: String) {
        try {
            viewModelScope.launch {
                val msg = postQuickLoginUseCase.buildUseCase(PostQuickLoginUseCase.Params(QuickPw(pw)))

                Log.d("quickLogin", msg.msg.toString())
                when (msg.msg) {
                    "fail" -> _isFailure.value = msg.msg!!
                    else -> _isSuccessLogin.value = msg.msg!!
                }

            }
        } catch (e: Exception) {
            Log.d("quickLogin", e.message.toString())
            _isFailure.postValue(e.message.toString())
        }
    }

    fun createAccount(nickName: String, pw: String) {
        try {
            viewModelScope.launch {
                val createAccount = CreateAccount(nickName, pw)
                val msg = postCreateAccountUseCase.buildUseCase(PostCreateAccountUseCase.Params(createAccount))

                Log.d("createAccount", msg.msg.toString())
                when (msg.msg) {
                    "success" -> _isSuccessCreate.value = msg.msg!!
                    "fail" -> _isFailure.value = msg.msg!!
                }
            }
        } catch (e: Exception) {
            Log.d("createAccount", e.message.toString())
            _isFailure.postValue(e.message.toString())
        }
    }
}