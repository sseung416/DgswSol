package com.example.a2ndproject.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.a2ndproject.R
import com.example.a2ndproject.databinding.MainFragmentBinding
import com.example.a2ndproject.ui.viewmodel.MainViewModel
import com.google.android.material.tabs.TabLayout
import kotlin.concurrent.thread

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
                // setTab(pos: Int) 메서드로 레이아웃을 변경함
                fragment.setTab(tab!!.position)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })

        /* 탭 레이아웃 5초마다 움직이게... */
        thread {
            while (true) {
                Thread.sleep(5000)

                requireActivity().runOnUiThread {
                    when (binding.tabLayoutMain.selectedTabPosition) {
                        0 -> {
                            binding.tabLayoutMain.getTabAt(1)!!.select()
                            fragment.setTab(1)
                        }
                        1 -> {
                            binding.tabLayoutMain.getTabAt(0)!!.select()
                            fragment.setTab(0)
                        }
                    }
                    Log.d("tq","tlfgod")
                }
            }
        }
    }
}