package com.example.a2ndproject.ui.viewmodel.fragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a2ndproject.ui.view.utils.isBlankAll
import com.example.a2ndproject.ui.view.utils.isNotBlankAll
import com.example.domain.entity.request.Login
import com.example.domain.entity.response.Msg
import com.example.domain.usecase.user.PostLoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val useCase: PostLoginUseCase
) : ViewModel() {

    val id = MutableLiveData<String>()
    val pw = MutableLiveData<String>()

    val idErr = MutableLiveData("")
    val pwErr = MutableLiveData("")

    val errMsg = MutableLiveData("")

    private val _isSuccess = MutableLiveData<String>()
    val isSuccess = _isSuccess

    private val _isFailure = MutableLiveData<String>()
    val isFailure = _isFailure

    fun login() {
        if (id.value.isNullOrBlank() || pw.value.isNullOrBlank()) {
            errMsg.value = "아이디/비밀번호를 입력해주세요."
            return
        }

        try {
            viewModelScope.launch {
                if (listOf(id.value, pw.value).isNotBlankAll() &&
                    listOf(idErr.value, pwErr.value).isBlankAll()) {

                    val login = Login(id.value!!, pw.value!!)
                    val msg = useCase.buildUseCase(PostLoginUseCase.Params(login))

                    _isSuccess.value = msg.msg!!
                }
            }
        } catch (e: Exception) {
            _isFailure.value = e.message
        }
    }
}