package com.example.a2ndproject.ui.view.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.example.a2ndproject.R
import com.example.a2ndproject.databinding.TransferChooseFragmentBinding
import com.example.a2ndproject.ui.view.base.BaseFragment
import com.example.a2ndproject.ui.view.utils.MessageUtil
import com.example.a2ndproject.ui.viewmodel.fragment.TransferViewModel

class TransferChooseFragment : BaseFragment<TransferChooseFragmentBinding>() {

    private val viewModel: TransferViewModel by activityViewModels()

    override fun getLayoutResId(): Int =
        R.layout.transfer_choose_fragment

    override fun setViewModel() {
        binding.vm = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observe()

        binding.btnConfirmTransferChoose.setOnClickListener {
            val account = viewModel.targetAccount.value

            if (account.isNullOrBlank()) {
                MessageUtil.showDialog(requireActivity(), "송금할 계좌를 입력해주세요.")
            } else {
                viewModel.transferAccountCheck()
            }
        }
    }

    private fun observe() = with(viewModel) {
        isSuccessCheckAccount.observe(viewLifecycleOwner) {
            navController.navigate(R.id.action_transferChooseFragment_to_numberPadMoneyFragment)
        }

        isFailureCheckAccount.observe(viewLifecycleOwner) {
            MessageUtil.showDialog(requireActivity(), "존재하지 않는 계좌입니다.")
        }
    }
}