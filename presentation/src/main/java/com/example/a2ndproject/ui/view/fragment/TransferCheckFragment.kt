package com.example.a2ndproject.ui.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.a2ndproject.R
import com.example.a2ndproject.databinding.TransferCheckFragmentBinding
import com.example.a2ndproject.ui.view.activity.MainActivity
import com.example.a2ndproject.ui.view.base.BaseFragment
import com.example.a2ndproject.ui.view.data.FragmentType
import com.example.a2ndproject.ui.viewmodel.fragment.TransferViewModel

class TransferCheckFragment : BaseFragment<TransferCheckFragmentBinding>() {

    private val viewModel: TransferViewModel by activityViewModels()

    override fun getLayoutResId(): Int =
        R.layout.transfer_check_fragment

    override fun setViewModel() {
        binding.vm = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCancelTransferCheck.setOnClickListener {
            requireActivity().startActivity(Intent(requireContext(), MainActivity::class.java))
        }

        binding.btnConfirmTransferCheck.setOnClickListener {
            navController.navigate(
                TransferCheckFragmentDirections.actionTransferCheckFragmentToNumberPadPinFragment(
                    FragmentType.PIN_ACCOUNT_PW.type
            ))
        }
    }
}
