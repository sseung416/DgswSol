package com.example.a2ndproject.ui.viewmodel.fragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.a2ndproject.R
import java.util.*

class NumberPadViewModel : ViewModel() {
    val numberList = MutableLiveData<Stack<Int>>()
    private val stack = Stack<Int>()

    fun addNumber(num: Int) {
        stack.push(num)
        numberList.value = stack
    }

    fun removeNumber() {
        stack.pop()
        numberList.value = stack
    }
}