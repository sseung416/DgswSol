package com.example.a2ndproject.ui.view.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.PagerSnapHelper
import com.example.a2ndproject.R
import com.example.a2ndproject.databinding.HomeTabSolFragmentBinding
import com.example.a2ndproject.ui.view.activity.CreateAccountActivity
import com.example.a2ndproject.ui.view.activity.HoldActivity
import com.example.a2ndproject.ui.view.activity.TransferActivity
import com.example.a2ndproject.ui.view.adapter.HomeTabRecyclerViewAdapter
import com.example.a2ndproject.ui.view.base.BaseFragment
import com.example.a2ndproject.ui.view.utils.MessageUtil
import com.example.a2ndproject.ui.viewmodel.fragment.HomeTabViewModel

class HomeTabSolFragment : BaseFragment<HomeTabSolFragmentBinding>() {

    private val viewModel: HomeTabViewModel by activityViewModels()

    private val adapter = HomeTabRecyclerViewAdapter()

    override fun getLayoutResId(): Int =
        R.layout.home_tab_sol_fragment

    override fun setViewModel() {}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observe()
        init()

        adapter.setOnClickTransferListener {
            val intent = Intent(requireActivity(), TransferActivity::class.java)
            intent.putExtra("from", it.accountID)
            startActivity(intent)
        }

        adapter.setOnClickHoldListener {
            val intent = Intent(requireActivity(), HoldActivity::class.java)
            intent.putExtra("from", it.accountID)
            startActivity(intent)
        }

        binding.btnTab.setOnClickListener {
            startActivity(Intent(requireActivity(), CreateAccountActivity::class.java))
        }
    }

    private fun init() {
        viewModel.getHomeAccount()

        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.rvAccountTab)

        binding.rvAccountTab.adapter = adapter

        binding.layoutEmptyTab.visibility = VISIBLE
        binding.layoutViewPagerTab.visibility = GONE
    }

    private fun observe() {
        viewModel.isSuccessGetHomeAccount.observe(viewLifecycleOwner) {
            adapter.setList(it)

            if (it.isNotEmpty()) {
                binding.layoutEmptyTab.visibility = GONE
                binding.layoutViewPagerTab.visibility = VISIBLE
            }
        }

        viewModel.isFailureGetHomeAccount.observe(viewLifecycleOwner) {
            MessageUtil.showDialog(requireActivity(), "?????? ????????? ??????????????? ??????????????????.\n?????? ????????? ?????????.");
        }
    }
}