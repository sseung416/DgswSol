package com.example.a2ndproject.ui.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.a2ndproject.R
import com.example.a2ndproject.databinding.LoginBasicFragmentBinding
import com.example.a2ndproject.ui.view.base.BaseFragment
import com.example.a2ndproject.ui.view.base.BaseViewModelFactory
import com.example.a2ndproject.ui.view.utils.Preference.token
import com.example.a2ndproject.ui.viewmodel.fragment.LoginViewModel
import com.example.domain.usecase.user.PostLoginUseCase
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginBasicFragment : BaseFragment<LoginBasicFragmentBinding>() {

    @Inject
    lateinit var postLoginUseCase: PostLoginUseCase

    private val viewModel: LoginViewModel by activityViewModels {
        BaseViewModelFactory(postLoginUseCase)
    }

    override fun getLayoutResId(): Int =
        R.layout.login_basic_fragment


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        observe()
    }

    private fun observe() = with(viewModel) {
        isSuccess.observe(viewLifecycleOwner) {
            token = it.token
        }

        isFailure.observe(viewLifecycleOwner) {

        }
    }
}