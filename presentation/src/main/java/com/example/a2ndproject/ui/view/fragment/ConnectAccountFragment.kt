package com.example.a2ndproject.ui.view.fragment

import androidx.fragment.app.activityViewModels
import com.example.a2ndproject.R
import com.example.a2ndproject.databinding.AddAccountConnectFragmentBinding
import com.example.a2ndproject.ui.view.base.BaseFragment
import com.example.a2ndproject.ui.viewmodel.fragment.AddAccountViewModel
import com.example.a2ndproject.ui.viewmodel.fragment.ConnectAccountViewModel

class ConnectAccountFragment : BaseFragment<AddAccountConnectFragmentBinding>() {

    private val viewModel: AddAccountViewModel by activityViewModels()

    override fun getLayoutResId(): Int =
        R.layout.add_account_connect_fragment

    override fun setViewModel() {
        binding.vm = viewModel
    }

}