package com.example.a2ndproject.ui.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.a2ndproject.R
import com.example.a2ndproject.databinding.AddAccountFragmentBinding
import com.example.a2ndproject.ui.view.base.BaseFragment
import com.example.a2ndproject.ui.viewmodel.fragment.AddAccountViewModel

class AddAccountFragment : BaseFragment<AddAccountFragmentBinding, AddAccountViewModel>() {

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): AddAccountFragmentBinding =
        AddAccountFragmentBinding.inflate(inflater, container, false)

    override fun getViewModelClass(): Class<AddAccountViewModel> = AddAccountViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnRegisterAddAccount.setOnClickListener {
            navigateToCreateAccount()
        }

        binding.btnConnectAddAccount.setOnClickListener {
            navigateToConnectThisAccount()
        }
    }

    private fun navigateToConnectThisAccount() {
        navController.navigate(R.id.action_addAccountFragment_to_connectThisAccountFragment)
    }

    private fun navigateToCreateAccount() {
        navController.navigate(R.id.action_addAccountFragment_to_createAccountFragment)
    }
}