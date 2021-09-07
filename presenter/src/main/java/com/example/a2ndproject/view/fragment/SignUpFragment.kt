/**
 * 회원가입을 위한 Sign Up Fragment Class
 * 아이디, 비밀번호, 휴대폰 번호, 주민등록번호(7번째까지), 이름(실명), 별명, 프로필 사진 필요
 *
 * @author 최승연
 * @date 2021-09-07
 * */
package com.example.a2ndproject.view.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.SCROLL_STATE_DRAGGING
import com.example.a2ndproject.R
import com.example.a2ndproject.databinding.SignUpFragmentBinding
import com.example.a2ndproject.view.adapter.SignUpViewPagerAdapter
import com.example.a2ndproject.view.viewmodel.SignUpViewModel

class SignUpFragment : Fragment() {

    private val navController by lazy {
        findNavController()
    }

    private lateinit var binding: SignUpFragmentBinding
    private lateinit var viewModel: SignUpViewModel

    private lateinit var adapter: SignUpViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.sign_up_fragment, container, false)
        init()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewPager()

        /* 확인 버튼 클릭 시 ViewPager의 다음 페이지로 넘어감 */
        binding.btnConfirmSignUp.setOnClickListener {
            when (binding.viewPagerSignUp.currentItem) {
                /* 마지막 페이지에서 버튼의 text를 가입하기로 바꾸기 위함 */
                1 -> binding.btnConfirmSignUp.text = "가입하기"

                /* 마지막 페이지에서 회원가입 완료 후 핀번호 설정 페이지로 넘어감 */
                2 -> navigateToPinNumber()

                /* 다음 페이지로 넘기기 위해 currentItem을 현재 currentItem에 1을 더함 */
                else -> binding.viewPagerSignUp.currentItem = binding.viewPagerSignUp.currentItem + 1
            }
        }
    }

    private fun init() {
        viewModel = ViewModelProvider(requireActivity()).get(SignUpViewModel::class.java)
    }

    /**
     * ViewPager를 초기화하는 메서드 - adapter, isUserInputEnabled 설정
     * */
    private fun initViewPager() {
        adapter = SignUpViewPagerAdapter()
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
}