package com.example.a2ndproject.ui.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.a2ndproject.R
import com.example.a2ndproject.databinding.ConnectAccountFragmentBinding
import com.example.a2ndproject.ui.view.activity.MainActivity
import com.example.a2ndproject.ui.view.adapter.ConnectRecyclerViewAdapter
import com.example.a2ndproject.ui.view.base.BaseFragment
import com.example.a2ndproject.ui.view.utils.MessageUtil
import com.example.a2ndproject.ui.viewmodel.fragment.ConnectAccountViewModel

class ConnectAccountFragment : BaseFragment<ConnectAccountFragmentBinding>() {

    private val viewModel: ConnectAccountViewModel by viewModels()
    private val adapter = ConnectRecyclerViewAdapter()

    override fun getLayoutResId(): Int =
        R.layout.connect_account_fragment

    override fun setViewModel() {
        binding.vm = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observe()

        viewModel.getAccountList()

        binding.rvConnect.adapter = adapter

        binding.btnConfirmConnect.setOnClickListener {
            requireActivity().startActivity(Intent(requireActivity(), MainActivity::class.java))
        }
    }

    private fun observe() = with (viewModel) {
        isSuccess.observe(viewLifecycleOwner) {
            adapter.setList(it)
        }

        isFailure.observe(viewLifecycleOwner) {
            MessageUtil.showDialog(requireActivity(), "계좌를 가져오는데 실패하였습니다.\n다시 시도해주세요.")
        }
    }
}