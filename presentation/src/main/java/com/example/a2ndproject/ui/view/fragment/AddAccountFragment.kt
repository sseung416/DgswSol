package com.example.a2ndproject.ui.view.fragment

import android.os.Bundle
import android.view.View
import com.example.a2ndproject.R
import com.example.a2ndproject.databinding.AddAccountFragmentBinding
import com.example.a2ndproject.ui.view.base.BaseFragment

class AddAccountFragment : BaseFragment<AddAccountFragmentBinding>() {

    override fun getLayoutResId(): Int =
        R.layout.add_account_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnRegisterAddAccount.setOnClickListener {
            navigateToCreateAccount()
        }

        binding.btnConnectAddAccount.setOnClickListener {
            navigateToCreateAccount()
        }
    }

    private fun navigateToCreateAccount() {
        navController.navigate(R.id.action_addAccountFragment_to_identityAuthFragment)
    }
}