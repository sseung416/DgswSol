package com.example.a2ndproject.ui.viewmodel.fragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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

    fun popAll() {
        while (stack.isNotEmpty()) {
            stack.pop()
        }
        numberList.value = stack
    }

    // 스택을 String으로 변환해 반환
    fun getNumber(): String =
        numberList.value!!.joinToString("", "")


}