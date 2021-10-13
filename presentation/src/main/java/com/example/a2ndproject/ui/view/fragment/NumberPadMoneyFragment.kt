package com.example.a2ndproject.ui.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.a2ndproject.R
import com.example.a2ndproject.databinding.NumberPadMoneyFragmentBinding
import com.example.a2ndproject.databinding.NumberPadPinFragmentBinding
import com.example.a2ndproject.ui.view.base.BaseFragment
import com.example.a2ndproject.ui.view.model.Account
import com.example.a2ndproject.ui.viewmodel.factory.NumberPadMoneyViewModelFactory
import com.example.a2ndproject.ui.viewmodel.fragment.NumberPadMoneyViewModel
import com.example.a2ndproject.ui.viewmodel.fragment.NumberPadViewModel
import java.util.*


class NumberPadMoneyFragment : BaseFragment<NumberPadMoneyFragmentBinding>() {

    private lateinit var account: Account

    private val numberPadViewModel: NumberPadViewModel by activityViewModels()
    private val moneyViewModel: NumberPadMoneyViewModel by activityViewModels {
        NumberPadMoneyViewModelFactory(account)
    }

    override fun getLayoutResId(): Int =
        R.layout.number_pad_money_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        account = arguments?.get("account") as Account
        binding.vm = moneyViewModel

        observe()
    }

    private fun observe() = with(moneyViewModel) {
        numberPadViewModel.numberList.observe(viewLifecycleOwner, {
            this.setMoney(it.lastElement())
        })

        money.observe(viewLifecycleOwner, {
            setMoneyKR()
        })


    }
}