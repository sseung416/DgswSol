package com.example.a2ndproject.ui.view.fragment

import com.example.a2ndproject.R
import com.example.a2ndproject.databinding.AddAccountCheckInfoFragmentBinding
import com.example.a2ndproject.ui.view.base.BaseFragment
import com.example.a2ndproject.ui.viewmodel.fragment.CreateAccountCheckInfoViewModel

class CheckInfoFragment : BaseFragment<AddAccountCheckInfoFragmentBinding>() {

    private lateinit var viewModel: CreateAccountCheckInfoViewModel

    override fun getLayoutResId(): Int =
        R.layout.add_account_check_info_fragment

}