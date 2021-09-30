/**
 * 회원가입을 위한 Sign Up Fragment Class
 * 아이디, 비밀번호, 휴대폰 번호, 주민등록번호(7번째까지), 이름(실명), 별명, 프로필 사진 필요
 *
 * @author 최승연
 * @date 2021-09-07
 * */
package com.example.a2ndproject.ui.view.fragment

import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.a2ndproject.R
import com.example.a2ndproject.databinding.SignUpFragmentBinding
import com.example.a2ndproject.ui.view.adapter.SignUpViewPagerAdapter
import com.example.a2ndproject.ui.view.base.BaseFragment
import com.example.a2ndproject.ui.viewmodel.fragment.SignUpViewModel
import kotlin.concurrent.thread

class SignUpFragment : BaseFragment<SignUpFragmentBinding, SignUpViewModel>() {

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): SignUpFragmentBinding = SignUpFragmentBinding.inflate(inflater, container, false)

    override fun getViewModelClass(): Class<SignUpViewModel> = SignUpViewModel::class.java

    private val adapter = SignUpViewPagerAdapter()

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

        // onCreateViewHolder가 이 코드보다 늦게 실행돼서 binding이 초기화가 안 됨 -> 따라서 무지성으로 sleep 함 ㅎㅎ
        // todo 다른 방법 찾아보기..
        thread {
            Thread.sleep(200)
            setIdEditTextListener()
            setPasswordEditTextListener()
            setPhoneNumberEditTextListener()
        }
    }

    private fun init() {
        binding.viewPagerSignUp.adapter = adapter

        /* ViewPager의 스와이프를 막음 */
        binding.viewPagerSignUp.isUserInputEnabled = false

        binding.lifecycleOwner = viewLifecycleOwner
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

    private fun setIdEditTextListener() {
        adapter.binding.etIdViewPagerItemSignUp.addTextChangedListener {
            adapter.binding.etLayoutIdViewPagerItemSignUp.error =
                viewModel.isValidId(it.toString().trim())
        }
    }

    private fun setPasswordEditTextListener() {
        adapter.binding.etPasswordViewPagerItemSignUp.addTextChangedListener {
            adapter.binding.etLayoutPasswordViewPagerItemSignUp.error =
                viewModel.isValidPassword(it.toString().trim())
        }
    }

    private fun setPhoneNumberEditTextListener() = adapter.binding.etPhoneNumberViewPagerItemSignUp.apply {
        addTextChangedListener(object : PhoneNumberFormattingTextWatcher(){})
    }

}