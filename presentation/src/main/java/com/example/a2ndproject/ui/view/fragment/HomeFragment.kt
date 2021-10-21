package com.example.a2ndproject.ui.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.a2ndproject.R
import com.example.a2ndproject.databinding.HomeFragmentBinding
import com.example.a2ndproject.ui.view.base.BaseFragment
import com.example.a2ndproject.ui.viewmodel.fragment.HomeViewModel
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.selects.select
import kotlin.concurrent.thread

class HomeFragment : BaseFragment<HomeFragmentBinding>() {

    private val viewModel: HomeViewModel by activityViewModels()

    override fun getLayoutResId(): Int =
        R.layout.home_fragment

    override fun setViewModel() {
        binding.vm = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initTabLayout()

        binding.btnCreateAccountHome.setOnClickListener {
            navigateToAddAccount()
        }

        binding.btnTransferHome.setOnClickListener {

        }
    }

    private fun initTabLayout() {
        val fragment = HomeTabFragment()

        // TabLayout
        parentFragmentManager.beginTransaction().replace(R.id.frame_layout_tab_main, fragment).commit()

        // 탭을 선택했을 때 이벤트 처리
//        binding.tabLayoutMain.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
//            override fun onTabSelected(tab: TabLayout.Tab?) {
//                // 한 프래그먼트를 돌려쓰기 때문에 TabLayoutFragment에 선언된
//                viewModel.position.value = tab!!.position
//            }
//
//            override fun onTabUnselected(tab: TabLayout.Tab?) {
//            }
//
//            override fun onTabReselected(tab: TabLayout.Tab?) {
//            }
//
//        })

        /* 탭 레이아웃 5초마다 움직이게... */
        // todo 뭔가 이상함
        thread {
            while (true) {
                Thread.sleep(5000)

                requireActivity().runOnUiThread {


                    when (binding.tabLayoutMain.selectedTabPosition) {
                        0 -> {
                            binding.tabLayoutMain.getTabAt(1)!!.select()
                            viewModel.position.value = 1
                        }
                        1 -> {
                            binding.tabLayoutMain.getTabAt(0)!!.select()
                            viewModel.position.value = 0
                        }
                    }
                }
            }
        }
    }

    private fun navigateToAddAccount() {
        navController.navigate(R.id.action_homeFragment_to_addAccountFragment)
    }

    private fun navigateToTransfer() {

    }


}