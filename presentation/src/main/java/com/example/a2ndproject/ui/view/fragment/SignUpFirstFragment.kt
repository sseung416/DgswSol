package com.example.a2ndproject.ui.view.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.example.a2ndproject.R
import com.example.a2ndproject.databinding.SignUpFirstFragmentBinding
import com.example.a2ndproject.ui.view.base.BaseFragment
import com.example.a2ndproject.ui.view.utils.MessageUtil
import com.example.a2ndproject.ui.view.utils.getString
import com.example.a2ndproject.ui.view.utils.getStringText
import com.example.a2ndproject.ui.viewmodel.fragment.SignUpViewModel

class SignUpFirstFragment : BaseFragment<SignUpFirstFragmentBinding>() {

    private val viewModel: SignUpViewModel by activityViewModels()

    override fun getLayoutResId(): Int =
        R.layout.sign_up_first_fragment

    override fun setViewModel() {
        binding.vm = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe()
    }

    private fun observe() = with(viewModel) {
        isSuccessIdCheck.observe(viewLifecycleOwner) {
            when (it) {
                this@SignUpFirstFragment.getStringText(R.string.server_res_success) -> {
                    MessageUtil.showToast(requireContext(), "사용가능한 아이디입니다 ㅠ")
                    idCheck.value = true
                }

                this@SignUpFirstFragment.getStringText(R.string.server_res_fail) -> {
                    MessageUtil.showToast(requireContext(), "중복된 아이디입니다 ㅠ")
                }
            }
        }

        currentItem.observe(viewLifecycleOwner) {
            if (it == 1)
                navController.navigate(R.id.action_signUpFirstFragment_to_signUpSecondFragment)
        }
    }
}