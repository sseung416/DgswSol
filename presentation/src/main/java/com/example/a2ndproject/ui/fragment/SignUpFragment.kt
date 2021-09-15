/**
 * 회원가입을 위한 Sign Up Fragment Class
 * 아이디, 비밀번호, 휴대폰 번호, 주민등록번호(7번째까지), 이름(실명), 별명, 프로필 사진 필요
 *
 * @author 최승연
 * @date 2021-09-07
 * */
package com.example.a2ndproject.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.inflate
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.a2ndproject.R
import com.example.a2ndproject.databinding.ActivityAddAccountBinding.inflate
import com.example.a2ndproject.databinding.SignUpFragmentBinding
import com.example.a2ndproject.ui.adapter.SignUpViewPagerAdapter
import com.example.a2ndproject.ui.base.BaseFragment
import com.example.a2ndproject.ui.viewmodel.SignUpViewModel

class SignUpFragment : BaseFragment<SignUpFragmentBinding, SignUpViewModel>() {

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): SignUpFragmentBinding = SignUpFragmentBinding.inflate(inflater, container, false)

    override fun getViewModelClass(): Class<SignUpViewModel> = SignUpViewModel::class.java

    private val adapter: SignUpViewPagerAdapter by lazy { SignUpViewPagerAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewPager()

        /* 확인 버튼 클릭 시 ViewPager의 다음 페이지로 넘어감 */
        binding.btnConfirmSignUp.setOnClickListener {
            when (binding.viewPagerSignUp.currentItem) {
                /* 중복 체크가 되었는지 확인 */
                0 -> {
                    val errorMsg = viewModel.isDuplicateCheck()

                    if (errorMsg != null) {
                        Toast.makeText(requireContext(), errorMsg, Toast.LENGTH_LONG).show()
                        return@setOnClickListener
                    }
                }

                /* 마지막 페이지에서 버튼의 text를 가입하기로 바꾸기 위함 */
                1 -> binding.btnConfirmSignUp.text = "가입하기"

                /* 마지막 페이지에서 회원가입 완료 후 핀번호 설정 페이지로 넘어감 */
                2 -> {
                    // TODO 회원가입 서버 통신 . . .
                    navigateToPinNumber()
                }
            }

            /* 다음 페이지로 이동을 위해 현재 페이지에서 1을 더함 */
            binding.viewPagerSignUp.currentItem += 1
        }
    }

    /**
     * ViewPager를 초기화하는 메서드
     * adapter, isUserInputEnabled, 어댑터 클릭 이벤트 설정
     * */
    private fun initViewPager() {
        binding.viewPagerSignUp.adapter = adapter

        /* ViewPager의 스와이프를 막음 */
        binding.viewPagerSignUp.isUserInputEnabled = false

        /* 아이디 중복 검사 버튼 클릭시 실행 */
        adapter.setOnClickSignUpIdListener {
            adapter.binding.apply {
                /* 아이디 정규식 검사 */
                etLayoutIdViewPagerItemSignUp.error = viewModel.isValidId(etIdViewPagerItemSignUp.text.toString())

                /* 오류가 없다면 중복체크를 완료 */
                if (etLayoutIdViewPagerItemSignUp.error == null) viewModel.isDoubleCheckedId = true
            }
        }

        /* 비밀번호 중복 검사 버튼 클릭시 실행 */
        adapter.setOnClickSignUpPasswordListener {
            adapter.binding.apply {
                /* 비밀번호 정규식 검사 */
                etLayoutPasswordViewPagerItemSignUp.error = viewModel.isValidPassword(etPasswordViewPagerItemSignUp.text.toString())

                /* 오류가 없다면 중복체크를 완료 */
                if (etLayoutPasswordViewPagerItemSignUp.error == null) viewModel.isDoubleCheckedPassword = true
            }
        }
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
}