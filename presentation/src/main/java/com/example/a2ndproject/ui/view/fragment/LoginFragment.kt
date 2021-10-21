package com.example.a2ndproject.ui.view.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.example.a2ndproject.R
import com.example.a2ndproject.databinding.LoginFragmentBinding
import com.example.a2ndproject.ui.view.base.BaseFragment
import com.example.a2ndproject.ui.view.base.BaseViewModelFactory
import com.example.a2ndproject.ui.viewmodel.fragment.LoginViewModel
import com.example.domain.usecase.user.PostLoginUseCase
import com.example.domain.usecase.user.PostQuickLoginUseCase
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

//@AndroidEntryPoint
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
            navController.navigate(R.id.action_loginFragment_to_pinNuberFragment)
        }
    }
}