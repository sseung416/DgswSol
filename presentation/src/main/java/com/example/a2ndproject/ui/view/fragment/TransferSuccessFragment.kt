package com.example.a2ndproject.ui.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.a2ndproject.R
import com.example.a2ndproject.databinding.TransferSuccessFragmentBinding
import com.example.a2ndproject.ui.view.activity.MainActivity
import com.example.a2ndproject.ui.view.base.BaseFragment

class TransferSuccessFragment : BaseFragment<TransferSuccessFragmentBinding>() {

    override fun getLayoutResId(): Int =
        R.layout.transfer_success_fragment

    override fun setViewModel() {}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnConfirmTransferSuccess.setOnClickListener {
            requireActivity().startActivity(Intent(requireContext(), MainActivity::class.java))
        }
    }
}