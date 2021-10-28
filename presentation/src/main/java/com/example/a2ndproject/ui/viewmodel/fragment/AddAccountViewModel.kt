package com.example.a2ndproject.ui.viewmodel.fragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.a2ndproject.ui.view.base.BaseViewModel

class AddAccountViewModel : ViewModel() {
    val name = MutableLiveData("")
    val birth = MutableLiveData("")
    val nickname = MutableLiveData("")
    val pw = ""


}