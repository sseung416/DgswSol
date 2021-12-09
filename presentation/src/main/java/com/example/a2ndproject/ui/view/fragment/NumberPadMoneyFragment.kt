package com.example.a2ndproject.ui.view.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.example.a2ndproject.R
import com.example.a2ndproject.databinding.NumberPadMoneyFragmentBinding
import com.example.a2ndproject.ui.view.base.BaseFragment
import com.example.a2ndproject.ui.view.utils.MessageUtil
import com.example.a2ndproject.ui.viewmodel.factory.NumberPadMoneyViewModelFactory
import com.example.a2ndproject.ui.viewmodel.fragment.NumberPadMoneyViewModel
import com.example.a2ndproject.ui.viewmodel.fragment.NumberPadViewModel
import com.example.a2ndproject.ui.viewmodel.fragment.TransferViewModel


class NumberPadMoneyFragment : BaseFragment<NumberPadMoneyFragmentBinding>() {

    private val transferViewModel: TransferViewModel by activityViewModels()
    private val numberPadViewModel: NumberPadViewModel by activityViewModels()
    private val moneyViewModel: NumberPadMoneyViewModel by activityViewModels {
        NumberPadMoneyViewModelFactory(transferViewModel.fromAccount)
    }

    override fun getLayoutResId(): Int =
        R.layout.number_pad_money_fragment

    override fun setViewModel() {
        binding.vm = moneyViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.vm = moneyViewModel

        observe()
    }

    private fun observe() {
        numberPadViewModel.numberList.observe(viewLifecycleOwner, {
            when {
                it.size >= 7 ->
                    moneyViewModel.moneyErr.value = "이체 한도 금액은 100만원 미만입니다."

                it.size == 0 ->
                    moneyViewModel.money.value = "0"

                else -> {
                    moneyViewModel.moneyErr.value = ""
                    moneyViewModel.setMoney(it)
                }
            }
        })

        numberPadViewModel.confirm.observe(viewLifecycleOwner) {
            if (it) {
                transferViewModel.money.value = Integer.parseInt(moneyViewModel.money.value)
                navController.navigate(R.id.action_numberPadMoneyFragment_to_transferCheckFragment)
            }
        }
    }
}