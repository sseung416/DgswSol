/**
 * Sign Up ViewModel Class
 *
 * @author 최승연
 * @date 2021-09-06
 * */
package com.example.a2ndproject.view.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignUpViewModel : ViewModel() {

    private lateinit var _inputError: MutableLiveData<String>

    fun isValidId(id: String?): Boolean {
        return true
    }

    fun isValidPassword(password: String?): Boolean {
        return true
    }
}