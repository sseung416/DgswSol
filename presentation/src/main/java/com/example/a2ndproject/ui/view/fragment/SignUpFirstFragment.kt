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
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                if (viewModel.idCheck.value!!) {
                    if (viewModel.errorMsg.value == "") {
                        binding.motionSignUpFirst.transitionToEnd()
                        binding.etPasswordSignUpFirst.requestFocus()
                    }
                } else {
                    viewModel.errorMsg.value = "중복 확인 점;"
                }
            }

            return@setOnEditorActionListener true
        }

        binding.etPasswordSignUpFirst.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                if (viewModel.errorMsg.value == "" && viewModel.idCheck.value!! && !viewModel.password.value.isNullOrBlank()) {
                    navController.navigate(R.id.action_signUpFirstFragment_to_signUpSecondFragment)
                }
            }

            return@setOnEditorActionListener true
        }
    }

    private fun observe() = with(viewModel) {
        isSuccessIdCheck.observe(viewLifecycleOwner) {
            MessageUtil.showToast(requireContext(), "사용가능한 아이디입니다 ㅠ")
            idCheck.value = true

            if (errorMsg.value == "아이디 중복 확인을 해주세요.") errorMsg.value = ""
        }

        /**
         * currentItem 사망하심
         * */
        currentItem.observe(viewLifecycleOwner) {
            when (it) {
                1 -> {
                    binding.motionSignUpFirst.transitionToEnd()
                    binding.etPasswordSignUpFirst.requestFocus()
                }

                2 -> navController.navigate(R.id.action_signUpFirstFragment_to_signUpSecondFragment)
            }
        }

        id.observe(viewLifecycleOwner) {
            /* 영어, 숫자를 포함한 3~12글자의 정규식 */
            val reg = "^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z[0-9]]{3,12}".toRegex()

            idErr.value = when {
                it == null -> ""

                it.isBlank() -> this@SignUpFirstFragment.getEmptyErrorString("아이디를")

                !it.matches(reg) -> this@SignUpFirstFragment.getNotRegularErrorString("아이디")

                else -> ""
            }

            // todo false
            idCheck.value = false

            errorMsg.value = idErr.value
        }

        password.observe(viewLifecycleOwner) {
            /* 영어, 숫자, 특수기호를 모두 포함한 8~12글자의 정규식 */
            val reg = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&])[A-Za-z[0-9]$@$!%*#?&]{8,12}$".toRegex()

            pwErr.value = when {
                it == null -> ""

                it.isBlank() -> this@SignUpFirstFragment.getEmptyErrorString("비밀번호를")

                !it.matches(reg) -> this@SignUpFirstFragment.getNotRegularErrorString("비밀번호")

                else -> ""
            }

            errorMsg.value = pwErr.value
        }

        // 에러 메세지가 비었을 때, 아이디 에러 값이나 비번 에러 값이 있다면 에러 메세지에 대입..................
        errorMsg.observe(viewLifecycleOwner) {
            if (errorMsg.value.isNullOrBlank()) {
                if (idErr.value!!.isNotBlank()) {
                    errorMsg.value = idErr.value
                } else if (pwErr.value!!.isNotBlank()) {
                    errorMsg.value = pwErr.value
                }
            }
        }
    }
}