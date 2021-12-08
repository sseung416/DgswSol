package com.example.a2ndproject.ui.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.a2ndproject.R
import com.example.a2ndproject.databinding.HomeFragmentBinding
import com.example.a2ndproject.ui.view.activity.CreateAccountActivity
import com.example.a2ndproject.ui.view.activity.TransferActivity
import com.example.a2ndproject.ui.view.base.BaseFragment
import com.example.a2ndproject.ui.viewmodel.fragment.HomeViewModel
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeFragmentBinding>() {

    override fun getLayoutResId(): Int =
        R.layout.home_fragment

    override fun setViewModel() {}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initTabLayout()

        binding.btnCreateAccountHome.setOnClickListener {
            navigateToAddAccount()
        }

        binding.btnTransferHome.setOnClickListener {
            navigateToTransfer()
        }
    }

    private fun initTabLayout() {
        parentFragmentManager.beginTransaction()
            .replace(R.id.frame_layout_tab_main, HomeTabSolFragment())
            .commit()

        binding.tabLayoutMain.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val fragment = when (tab?.position) {
                    0 -> HomeTabSolFragment()
                    1 -> HomeTabOpenBankingFragment()
                    else -> return
                }

                parentFragmentManager.beginTransaction()
                    .replace(R.id.frame_layout_tab_main, fragment)
                    .commit()
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })

        /* 탭 레이아웃 5초마다 움직이게... */
//        CoroutineScope(Dispatchers.Default).launch {
//            while (true) {
//                when (binding.tabLayoutMain.selectedTabPosition) {
//                    0 -> {
//                        binding.tabLayoutMain.getTabAt(1)!!.select()
//                        viewModel.position.value = 1
//                    }
//
//                    1 -> {
//                        binding.tabLayoutMain.getTabAt(0)!!.select()
//                        viewModel.position.value = 0
//                    }
//                }
//            }
//        }
    }

    private fun navigateToAddAccount() {
        requireActivity().startActivity(Intent(requireActivity(), CreateAccountActivity::class.java))
    }

    private fun navigateToTransfer() {
        requireActivity().startActivity(Intent(requireActivity(), TransferActivity::class.java))
    }
}