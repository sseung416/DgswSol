package com.example.a2ndproject.ui.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import com.example.a2ndproject.R
import com.example.a2ndproject.databinding.NumberPadMoneyFragmentBinding
import com.example.a2ndproject.ui.view.base.BaseFragment
import com.example.a2ndproject.ui.viewmodel.fragment.TransferViewModel

class TransferMoneyFragment : BaseFragment<NumberPadMoneyFragmentBinding>() {

    val transferViewModel: TransferViewModel by activityViewModels()

    override fun getLayoutResId(): Int =
        R.layout.transfer_money_fragment

    override fun setViewModel() {}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {
        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainerView, NumberPadMoneyFragment())
            .commit()
    }

    private fun navigateToChoose() {
        navController.navigate(R.id.action_transferMoneyFragment_to_transferChooseFragment)
    }
}