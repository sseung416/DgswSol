/**
 * 회원가입을 위한 Sign Up Fragment Class
 * 아이디, 비밀번호, 휴대폰 번호, 주민등록번호(7번째까지), 이름(실명), 별명, 프로필 사진 필요
 *
 * @author 최승연
 * @date 2021-09-07
 * */
package com.example.a2ndproject.ui.fragment

import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import android.telephony.PhoneNumberUtils
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.inflate
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.a2ndproject.R
import com.example.a2ndproject.databinding.ActivityAddAccountBinding.bind
import com.example.a2ndproject.databinding.ActivityAddAccountBinding.inflate
import com.example.a2ndproject.databinding.SignUpFragmentBinding
import com.example.a2ndproject.ui.adapter.SignUpViewPagerAdapter
import com.example.a2ndproject.ui.base.BaseFragment
import com.example.a2ndproject.ui.viewmodel.SignUpViewModel
import com.google.android.material.internal.TextWatcherAdapter
import com.google.android.material.textfield.TextInputLayout

class SignUpFragment : BaseFragment<SignUpFragmentBinding, SignUpViewModel>() {

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): SignUpFragmentBinding = SignUpFragmentBinding.inflate(inflater, container, false)

    override fun getViewModelClass(): Class<SignUpViewModel> = SignUpViewModel::class.java

    private val adapter: SignUpViewPagerAdapter by lazy { SignUpViewPagerAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()

        /* 확인 버튼 클릭 시 ViewPager의 다음 페이지로 넘어감 */
        binding.btnConfirmSignUp.setOnClickListener {
            when (binding.viewPagerSignUp.currentItem) {
                /* 중복 체크 버튼을 눌렀는지 확인 */
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

        setIdEditTextListener()
        setPasswordEditTextListener()
        setPhoneNumberEditTextListener()
    }

    private fun init() {
        binding.viewPagerSignUp.adapter = adapter

        /* ViewPager의 스와이프를 막음 */
        binding.viewPagerSignUp.isUserInputEnabled = false
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

    private fun setIdEditTextListener() = adapter.binding.etIdViewPagerItemSignUp.apply {
        addTextChangedListener {
            adapter.binding.etLayoutIdViewPagerItemSignUp.error =
                viewModel.isValidId(it.toString().trim())
        }

        /* 엔터키 눌렀을 때 이벤트 처리: 패스워드 editText 로 focus. */
        setOnKeyListener { _, keyCode, _ ->
            return@setOnKeyListener when (keyCode) {
                KeyEvent.KEYCODE_ENTER -> {
                    adapter.binding.etPasswordViewPagerItemSignUp.isFocusable = true
                    true
                }
                else -> false
            }
        }
    }

    private fun setPasswordEditTextListener() = adapter.binding.etPasswordViewPagerItemSignUp.apply {
        addTextChangedListener {
            adapter.binding.etLayoutPasswordViewPagerItemSignUp.error =
                viewModel.isValidPassword(text.toString().trim())
        }

        /* 엔터키 눌렀을 때 이벤트 처리: 패스워드 editText 로 focus. */
        setOnKeyListener { _, keyCode, _ ->
            if (keyCode == KeyEvent.KEYCODE_ENTER) {
                // todo(키보드 숨기기)
                return@setOnKeyListener true
            }
            return@setOnKeyListener false
        }
    }

    private fun setPhoneNumberEditTextListener() = adapter.binding.etPhoneNumberViewPagerItemSignUp.apply {
        addTextChangedListener(object : PhoneNumberFormattingTextWatcher(){})
    }

}