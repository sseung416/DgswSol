package com.example.a2ndproject.ui.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.a2ndproject.R
import com.example.a2ndproject.databinding.LoginFragmentBinding
import com.example.a2ndproject.ui.view.base.BaseFragment
import com.example.a2ndproject.ui.view.data.FragmentType
import com.example.a2ndproject.ui.view.utils.MessageUtil
import com.example.a2ndproject.ui.view.utils.Preference.token
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<LoginFragmentBinding>() {

    override fun getLayoutResId(): Int =
        R.layout.login_fragment

    override fun setViewModel() {}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.e("token", token)

        binding.layoutBasicLogin.setOnClickListener {
            navController.navigate(R.id.action_loginFragment_to_loginBasicFragment)
        }

        binding.layoutQuickLogin.setOnClickListener {
            if (token.isBlank()) {
                MessageUtil.showDialog(requireActivity(), "아이디/비밀번호 로그인 후 이용하실 수 있습니다.")
            } else {
                navController.navigate(LoginFragmentDirections.actionLoginFragmentToPinNumberFragment(
                    FragmentType.PIN_QUICK_SIGN_IN.type
                ))
            }
        }
    }
}