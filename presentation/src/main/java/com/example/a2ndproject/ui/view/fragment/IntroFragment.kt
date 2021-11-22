/**
 * 로그인이나 회원가입 전 서비스를 간단하게 소개하는 Intro Fragment Class
 *
 * @author 최승연
 * @date 2021-09-06
 * */
package com.example.a2ndproject.ui.view.fragment

import android.os.Bundle
import android.view.View
import com.example.a2ndproject.R
import com.example.a2ndproject.databinding.IntroFragmentBinding
import com.example.a2ndproject.ui.view.base.BaseFragment

class IntroFragment : BaseFragment<IntroFragmentBinding>() {

    override fun getLayoutResId(): Int =
        R.layout.intro_fragment

    override fun setViewModel() {}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLoginIntro.setOnClickListener {
            navigateToLogin()
        }

        binding.btnSignUpIntro.setOnClickListener {
            navigateToSignUp()
        }
    }

    private fun navigateToSignUp() {
        navController.navigate(R.id.action_introFragment_to_signUpFragment)
    }

    private fun navigateToLogin() {
        navController.navigate(R.id.action_introFragment_to_loginFragment)
    }
}