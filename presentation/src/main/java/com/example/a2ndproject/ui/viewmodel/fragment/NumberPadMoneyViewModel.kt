package com.example.a2ndproject.ui.viewmodel.fragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.a2ndproject.ui.view.model.Account
import java.text.NumberFormat
import java.util.*

class NumberPadMoneyViewModel(
    val account: Account
) : ViewModel() {

    private var moneyText: String = ""
    var moneyKR: String = ""

    val money = MutableLiveData<String>()
    val moneyErr = MutableLiveData<String>()

    fun setMoney(moneyStack: Stack<Int>) {
        moneyText = ""
        moneyStack.forEach { num ->
            moneyText += num
        }

//        NumberFormat.getInstance(Locale.getDefault()).format(moneyText)

        money.value = moneyText
    }

    fun setMoneyKR() {
        var index = -1
        var kr = ""

        while (++index != moneyText.lastIndex) {
            kr += when (moneyText[index]) {
                '1' -> "일"
                '2' -> "이"
                '3' -> "삼"
                '4' -> "사"
                '5' -> "오"
                '6' -> "육"
                '7' -> "칠"
                '8' -> "팔"
                '9' -> "구"
                '0' -> ""
                else -> ""
            }

            if (moneyText[index] != '0') {
                moneyText += when (moneyText.length - index) {
                    5 -> "만 "
                    4 -> "천 "
                    3 -> "백 "
                    2 -> "십 "
                    else -> ""
                }
            }
        }

        moneyKR = kr
    }

}