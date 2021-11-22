package com.example.a2ndproject.ui.view.fragment

import android.os.Bundle
import android.view.View
import com.example.a2ndproject.R
import com.example.a2ndproject.databinding.LoginFragmentBinding
import com.example.a2ndproject.ui.view.base.BaseFragment
import com.example.a2ndproject.ui.view.data.FragmentType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<LoginFragmentBinding>() {

    override fun getLayoutResId(): Int =
        R.layout.login_fragment

    override fun setViewModel() {}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.layoutBasicLogin.setOnClickListener {
            navController.navigate(R.id.action_loginFragment_to_loginBasicFragment)
        }

        binding.layoutQuickLogin.setOnClickListener {
            navController.navigate(LoginFragmentDirections.actionLoginFragmentToPinNumberFragment(
                FragmentType.PIN_QUICK_SIGN_IN.type
            ))
        }
    }


}