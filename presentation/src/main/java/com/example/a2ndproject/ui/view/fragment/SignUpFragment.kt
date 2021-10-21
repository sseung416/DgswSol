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
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.a2ndproject.R
import com.example.a2ndproject.databinding.SignUpFragmentBinding
import com.example.a2ndproject.ui.view.adapter.SignUpViewPagerAdapter
import com.example.a2ndproject.ui.view.base.BaseFragment
import com.example.a2ndproject.ui.viewmodel.fragment.SignUpViewModel

class SignUpFragment : BaseFragment<SignUpFragmentBinding>() {
    private val viewModel: SignUpViewModel by activityViewModels {
        ViewModelProvider.AndroidViewModelFactory(requireActivity().application)
    }

    override fun getLayoutResId(): Int =
        R.layout.sign_up_fragment

    override fun setViewModel() {
        binding.vm = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    /**
     * 회원가입 후 핀번호 설정하도록 PinNumberFragment로 이동하는 메서드
     * */
    private fun navigateToPinNumber() {
        /* 핀 번호를 설정 하는 지, 로그인 하는 지에 따라 type이 달라짐 */
        val bundle = Bundle()
        bundle.putInt("type", 0)

        navController.navigate(R.id.action_signUpFragment_to_pinNuberFragment, bundle)
    }

//    private fun setIdEditTextListener() {
//        adapter.binding.etIdViewPagerItemSignUp.addTextChangedListener {
//            adapter.binding.etLayoutIdViewPagerItemSignUp.error =
//                viewModel.isValidId(it.toString().trim())
//        }
//    }
//
//    private fun setPasswordEditTextListener() {
//        adapter.binding.etPasswordViewPagerItemSignUp.addTextChangedListener {
//            adapter.binding.etLayoutPasswordViewPagerItemSignUp.error =
//                viewModel.isValidPassword(it.toString().trim())
//        }
//    }

//    private fun setPhoneNumberEditTextListener() = adapter.binding.etPhoneNumberViewPagerItemSignUp.apply {
//        addTextChangedListener(object : PhoneNumberFormattingTextWatcher(){})
//    }

    private fun observe() = with(viewModel) {
        idError.observe(viewLifecycleOwner) {

        }
    }

}