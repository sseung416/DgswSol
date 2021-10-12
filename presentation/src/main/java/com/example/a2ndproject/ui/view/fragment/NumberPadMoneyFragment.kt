package com.example.a2ndproject.ui.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.a2ndproject.R
import com.example.a2ndproject.databinding.NumberPadMoneyFragmentBinding
import com.example.a2ndproject.databinding.NumberPadPinFragmentBinding
import com.example.a2ndproject.ui.view.base.BaseFragment


class NumberPadMoneyFragment : BaseFragment<NumberPadMoneyFragmentBinding>() {

    override fun getLayoutResId(): Int =
        R.layout.number_pad_money_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}