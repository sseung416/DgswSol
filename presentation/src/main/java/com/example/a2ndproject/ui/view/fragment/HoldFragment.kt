package com.example.a2ndproject.ui.view.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import com.example.a2ndproject.R
import com.example.a2ndproject.databinding.HoldFragmentBinding
import com.example.a2ndproject.ui.view.activity.MainActivity
import com.example.a2ndproject.ui.view.base.BaseFragment
import com.example.a2ndproject.ui.view.utils.MessageUtil
import com.example.a2ndproject.ui.viewmodel.fragment.HoldViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HoldFragment : BaseFragment<HoldFragmentBinding>() {

    private val viewModel: HoldViewModel by activityViewModels()

    override fun getLayoutResId(): Int =
        R.layout.hold_fragment

    override fun setViewModel() {}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        observe()

        binding.btnConfirmHold.setOnClickListener {
            viewModel.hold(
                Integer.parseInt(binding.etHold.text.toString()),
                "",
                ""
            )
        }
    }

    private fun init() {
        viewModel.getAccountList();
    }

    private fun observe() = with (viewModel) {
        isSuccess.observe(viewLifecycleOwner) {
            MessageUtil.showDialog(requireActivity(), "${binding.etHold.text}원을 가져왔습니다.")
            startActivity(Intent(requireActivity(), MainActivity::class.java))
        }

        isFailure.observe(viewLifecycleOwner) {
            MessageUtil.showDialog(requireActivity(), "가져오기를 실패하였습니다.\n($it)")
        }

        isSuccessAccount.observe(viewLifecycleOwner) {
            Log.d("isSuccessAccount", it.toString())
        }

        isFailureAccount.observe(viewLifecycleOwner) {
            MessageUtil.showDialog(requireActivity(), "계좌 목록을 가져오는데 실패했습니다.\n($it)")
        }
    }
}