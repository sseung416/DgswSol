package com.example.a2ndproject.ui.view.fragment

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.activityViewModels
import com.example.a2ndproject.R
import com.example.a2ndproject.databinding.SignUpFirstFragmentBinding
import com.example.a2ndproject.ui.view.base.BaseFragment
import com.example.a2ndproject.ui.view.utils.MessageUtil
import com.example.a2ndproject.ui.view.utils.getEmptyErrorString
import com.example.a2ndproject.ui.view.utils.getNotRegularErrorString
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

        binding.etIdViewPagerItemSignUp.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                if (viewModel.errorMsg.value == "" && viewModel.idCheck.value!!) {
                    viewModel.currentItem.value = 1
                    return@setOnEditorActionListener true
                }
            }

            return@setOnEditorActionListener false
        }

        binding.etPasswordViewPagerItemSignUp.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                if (viewModel.errorMsg.value == "" && viewModel.idCheck.value!!) {
                    viewModel.currentItem.value = 2
                    return@setOnEditorActionListener true
                }
            }

            return@setOnEditorActionListener false
        }
    }

    private fun observe() = with(viewModel) {
        isSuccessIdCheck.observe(viewLifecycleOwner) {
            when (it) {
                this@SignUpFirstFragment.getString(R.string.server_res_success) -> {
                    MessageUtil.showToast(requireContext(), "사용가능한 아이디입니다 ㅠ")
                }

                this@SignUpFirstFragment.getString(R.string.server_res_fail) -> {
                    viewModel.errorMsg.value = "중복된 아이디입니다."
                }
            }
        }

        currentItem.observe(viewLifecycleOwner) {
            when (it) {
                1 -> {
                    binding.motionSignUpFirst.transitionToEnd()
                    // todo 다음 editText로 focus 하는 기능
                }

                2 -> navController.navigate(R.id.action_signUpFirstFragment_to_signUpSecondFragment)
            }
        }

        id.observe(viewLifecycleOwner) {
            /* 영어, 숫자를 포함한 3~12글자의 정규식 */
            val reg = "^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z[0-9]]{3,12}".toRegex()

            errorMsg.value = when {
                it.isBlank() -> this@SignUpFirstFragment.getEmptyErrorString("아이디")

                !it.matches(reg) -> this@SignUpFirstFragment.getNotRegularErrorString("아이디")

                else -> ""
            }

//            idCheck.value = false
        }

        password.observe(viewLifecycleOwner) {
            /* 영어, 숫자, 특수기호를 모두 포함한 8~12글자의 정규식 */
            val reg = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&])[A-Za-z[0-9]$@$!%*#?&]{8,12}$".toRegex()

            errorMsg.value = when {
                it.isBlank() -> this@SignUpFirstFragment.getEmptyErrorString("비밀번호")

                !it.matches(reg) -> this@SignUpFirstFragment.getNotRegularErrorString("비밀번호")

                else -> ""
            }
        }
    }
}