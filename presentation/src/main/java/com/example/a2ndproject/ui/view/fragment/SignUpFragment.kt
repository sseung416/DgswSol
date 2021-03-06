/**
 * 회원가입을 위한 Sign Up Fragment Class
 * 아이디, 비밀번호, 휴대폰 번호, 주민등록번호(7번째까지), 이름(실명), 별명, 프로필 사진 필요
 *
 * @author 최승연
 * @date 2021-09-07
 * */
package com.example.a2ndproject.ui.view.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.example.a2ndproject.R
import com.example.a2ndproject.databinding.SignUpFragmentBinding
import com.example.a2ndproject.ui.view.base.BaseFragment
import com.example.a2ndproject.ui.view.data.FragmentType
import com.example.a2ndproject.ui.view.utils.Preference.token
import com.example.a2ndproject.ui.viewmodel.fragment.SignUpViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : BaseFragment<SignUpFragmentBinding>() {

    private val viewModel: SignUpViewModel by activityViewModels()

    override fun getLayoutResId(): Int =
        R.layout.sign_up_fragment

    override fun setViewModel() {
        binding.vm = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observe()

        binding.btnConfirmSignUp.setOnClickListener {
            viewModel.signUp()
        }
    }

    private fun observe() = with(viewModel) {
        isSuccessPostSignUp.observe(viewLifecycleOwner) {
            token = it
            navController.navigate(SignUpFragmentDirections.actionSignUpFragmentToPinNumberFragment(
                FragmentType.PIN_QUICK_SIGN_UP.type
            ))
        }
    }

}