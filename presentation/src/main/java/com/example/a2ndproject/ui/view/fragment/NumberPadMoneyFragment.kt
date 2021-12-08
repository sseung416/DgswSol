package com.example.a2ndproject.ui.view.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.example.a2ndproject.R
import com.example.a2ndproject.databinding.NumberPadMoneyFragmentBinding
import com.example.a2ndproject.ui.view.base.BaseFragment
import com.example.a2ndproject.ui.view.model.Account
import com.example.a2ndproject.ui.view.utils.MessageUtil
import com.example.a2ndproject.ui.viewmodel.factory.NumberPadMoneyViewModelFactory
import com.example.a2ndproject.ui.viewmodel.fragment.NumberPadMoneyViewModel
import com.example.a2ndproject.ui.viewmodel.fragment.NumberPadViewModel


class NumberPadMoneyFragment : BaseFragment<NumberPadMoneyFragmentBinding>() {

    private lateinit var account: Account

    private val numberPadViewModel: NumberPadViewModel by activityViewModels()
    private val moneyViewModel: NumberPadMoneyViewModel by activityViewModels {
        NumberPadMoneyViewModelFactory(Account(R.drawable.ic_toss_logo, "토스", "0000-0000-000-0"))
    }

    override fun getLayoutResId(): Int =
        R.layout.number_pad_money_fragment

    override fun setViewModel() {
        binding.vm = moneyViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        account = arguments?.get("account") as Account
        binding.vm = moneyViewModel

        observe()
    }

    private fun observe() = with(moneyViewModel) {
        numberPadViewModel.numberList.observe(viewLifecycleOwner, {
            when {
                it.size >= 7 ->
                    moneyErr.value = "이체 한도 금액은 100만원 미만입니다."

                it.size == 0 ->
                    money.value = "0"

                else -> {
                    moneyErr.value = ""
                    this.setMoney(it)
                }
            }
        })

    }
}