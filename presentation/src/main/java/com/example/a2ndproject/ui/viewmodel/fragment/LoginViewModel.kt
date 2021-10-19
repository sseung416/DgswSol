package com.example.a2ndproject.ui.viewmodel.fragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a2ndproject.ui.view.base.BaseViewModel
import com.example.a2ndproject.ui.view.utils.isBlankAll
import com.example.a2ndproject.ui.view.utils.isNotBlankAll
import com.example.domain.entity.request.Login
import com.example.domain.entity.response.Token
import com.example.domain.usecase.user.PostLoginUseCase
import kotlinx.coroutines.launch

class LoginViewModel(
    override val useCase: PostLoginUseCase
) : BaseViewModel<PostLoginUseCase, Token>() {

    val id = MutableLiveData<String>()
    val pw = MutableLiveData<String>()

    val idErr = MutableLiveData("")
    val pwErr = MutableLiveData("")

    fun login() {
        viewModelScope.launch {
            if (listOf(id.value, pw.value).isNotBlankAll() &&
                listOf(idErr.value, pwErr.value).isBlankAll()) {

                val login = Login(id.value!!, pw.value!!)
                useCase.buildUseCase(PostLoginUseCase.Params(login))
                _isSuccess.value = Token("sibal")
            }
        }
    }
}