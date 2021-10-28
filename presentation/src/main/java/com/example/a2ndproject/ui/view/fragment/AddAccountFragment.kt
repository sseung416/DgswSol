package com.example.a2ndproject.ui.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.a2ndproject.R
import com.example.a2ndproject.databinding.AddAccountFragmentBinding
import com.example.a2ndproject.ui.view.base.BaseFragment
import com.example.a2ndproject.ui.view.data.FragmentType

/**
 * 기존 계좌를 추가 혹은 계좌를 개설을 선택하는 프래그먼트
 * 계좌 개설: AddAccount - IdentityAuth - CheckInfo - NumberPadPin - CreateAccountSuccess - Home
 * 계좌 추가: AddAccount - IdentityAuth - ConnectAccount - Home
 * */
class AddAccountFragment : BaseFragment<AddAccountFragmentBinding>() {

    override fun getLayoutResId(): Int =
        R.layout.add_account_fragment

    override fun setViewModel() {}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnRegisterAddAccount.setOnClickListener {
            navigateToIdentityAuth(FragmentType.IDENTITY_AUTH_CREATE.type)
        }

        binding.btnConnectAddAccount.setOnClickListener {
            navigateToIdentityAuth(FragmentType.IDENTITY_AUTH_CONNECT.type)
        }
    }

    private fun navigateToIdentityAuth(type: Int) {
        navController.navigate(AddAccountFragmentDirections.actionAddAccountFragmentToIdentityAuthFragment(type))
    }
}