package com.example.a2ndproject.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.a2ndproject.R
import com.example.a2ndproject.databinding.MainFragmentBinding
import com.example.a2ndproject.ui.viewmodel.MainViewModel
import com.google.android.material.tabs.TabLayout

class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: MainFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initTabLayout()
    }

    private fun initTabLayout() {
        val fragment = TabLayoutFragment()

        // TabLayout
        parentFragmentManager.beginTransaction().replace(R.id.frame_layout_tab_main, fragment).commit()

        // 탭을 선택했을 때 이벤트 처리
        binding.tabLayoutMain.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                // 한 프래그먼트를 돌려쓰기 때문에 TabLayoutFragment에 선언된
                // setTab(pos: Int) 메서드로 뷰를 변경함
                fragment.setTab(tab!!.position)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })
    }
}