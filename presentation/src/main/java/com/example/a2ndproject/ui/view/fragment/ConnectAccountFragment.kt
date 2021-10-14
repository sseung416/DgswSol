package com.example.a2ndproject.ui.view.fragment

import com.example.a2ndproject.R
import com.example.a2ndproject.databinding.AddAccountConnectFragmentBinding
import com.example.a2ndproject.ui.view.base.BaseFragment
import com.example.a2ndproject.ui.viewmodel.fragment.ConnectAccountViewModel

class ConnectAccountFragment : BaseFragment<AddAccountConnectFragmentBinding>() {

    override fun getLayoutResId(): Int =
        R.layout.add_account_connect_fragment

    private lateinit var viewModel: ConnectAccountViewModel

}