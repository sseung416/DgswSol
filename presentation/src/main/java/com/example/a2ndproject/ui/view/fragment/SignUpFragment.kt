/**
 * 회원가입을 위한 Sign Up Fragment Class
 * 아이디, 비밀번호, 휴대폰 번호, 주민등록번호(7번째까지), 이름(실명), 별명, 프로필 사진 필요
 *
 * @author 최승연
 * @date 2021-09-07
 * */
package com.example.a2ndproject.ui.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.a2ndproject.R
import com.example.a2ndproject.databinding.SignUpFragmentBinding
import com.example.a2ndproject.ui.view.activity.IntroActivity
import com.example.a2ndproject.ui.view.activity.MainActivity
import com.example.a2ndproject.ui.view.adapter.SignUpViewPagerAdapter
import com.example.a2ndproject.ui.view.base.BaseFragment
import com.example.a2ndproject.ui.view.data.FragmentType
import com.example.a2ndproject.ui.view.utils.MessageUtil
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
    }

    private fun observe() = with(viewModel) {

        // 다음 화면으로 이동
//        currentItem.observe(viewLifecycleOwner) {
//            if (it == 3)
//                requireActivity().startActivity(Intent(requireActivity(), MainActivity::class.java))
////                navController.navigate(R.id.action_signUpFragment_to_pinNuberFragment)
//        }

        isSuccessPostSignUp.observe(viewLifecycleOwner) {
            token = it
            navController.navigate(SignUpFragmentDirections.actionSignUpFragmentToPinNumberFragment(
                FragmentType.PIN_QUICK_SIGN_UP.type
            ))
        }
    }

}