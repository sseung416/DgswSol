/**
 * 로그인이나 회원가입 전 서비스를 간단하게 소개하는 Intro Fragment Class
 *
 * @author 최승연
 * @date 2021-09-06
 * */
package com.example.a2ndproject.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.a2ndproject.R
import com.example.a2ndproject.ui.adapter.IntroViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class IntroFragment : Fragment() {

    private val navController: NavController by lazy {
        findNavController()
    }

    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    private lateinit var startBtn: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.intro_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init(view)

        startBtn.setOnClickListener {
            navigateToSignUp()
        }
    }

    /**
     * 각각의 변수를 초기화하고, TabLayout과 ViewPager 연결 및 ViewPager OnPageChangeCallBack 리스너 구현
     *
     * @param view : intro_fragment의 view
     * */
    private fun init(view: View) {
        viewPager = view.findViewById(R.id.view_pager_intro)
        tabLayout = view.findViewById(R.id.tab_layout_intro)
        startBtn = view.findViewById(R.id.btn_start_intro)

        // TODO 임의의 값 대신 유의미한 값으로 수정하기
        viewPager.adapter = IntroViewPagerAdapter(listOf("1111", "222", "3333"))

        /* TabLayout과 ViewPager 연결 */
        TabLayoutMediator(tabLayout, viewPager) { _, _ -> }.attach()

        /* ViewPager의 마지막 페이지일 때, 시작 버튼을 보이게 함 */
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                if (position == 2) startBtn.visibility = View.VISIBLE
                else startBtn.visibility = View.INVISIBLE
            }
        })
    }

    /**
     * SignUpFragment로 이동
     * */
    private fun navigateToSignUp() {
        navController.navigate(R.id.action_introFragment_to_signUpFragment)
    }

}