package com.example.a2ndproject.ui.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.a2ndproject.R
import com.example.a2ndproject.databinding.LoginBasicFragmentBinding
import com.example.a2ndproject.ui.view.activity.MainActivity
import com.example.a2ndproject.ui.view.base.BaseFragment
import com.example.a2ndproject.ui.view.data.FragmentType
import com.example.a2ndproject.ui.view.utils.MessageUtil
import com.example.a2ndproject.ui.view.utils.Preference.token
import com.example.a2ndproject.ui.view.utils.getStringText
import com.example.a2ndproject.ui.viewmodel.fragment.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginBasicFragment : BaseFragment<LoginBasicFragmentBinding>() {

    private val viewModel: LoginViewModel by viewModels()

    override fun getLayoutResId(): Int =
        R.layout.login_basic_fragment

    override fun setViewModel() {
        binding.vm = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        observe()
    }

    private fun observe() = with(viewModel) {
        isSuccess.observe(viewLifecycleOwner) {
            when (it) {
                "success" -> {
                    token = it
                    startActivity(Intent(requireActivity(), MainActivity::class.java))
                }

                "fail" -> viewModel.errMsg.value = "아이디/비밀번호가 맞지 않습니다."
            }
        }

        isFailure.observe(viewLifecycleOwner) {
            MessageUtil.showDialog(requireActivity(), "알림", getStringText(R.string.fail_server))
        }
    }
}