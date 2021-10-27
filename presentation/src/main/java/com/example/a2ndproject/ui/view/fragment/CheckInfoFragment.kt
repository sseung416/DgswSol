package com.example.a2ndproject.ui.view.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.example.a2ndproject.R
import com.example.a2ndproject.databinding.AddAccountCheckInfoFragmentBinding
import com.example.a2ndproject.ui.view.base.BaseFragment
import com.example.a2ndproject.ui.view.data.FragmentType
import com.example.a2ndproject.ui.viewmodel.fragment.CheckInfoViewModel

class CheckInfoFragment : BaseFragment<AddAccountCheckInfoFragmentBinding>() {

    private val viewModel: CheckInfoViewModel by activityViewModels()

    override fun getLayoutResId(): Int =
        R.layout.add_account_check_info_fragment

    override fun setViewModel() {
        binding.vm = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnConfirmCheckInfo.setOnClickListener {
            navController.navigate(CheckInfoFragmentDirections.actionCheckInfoFragmentToNumberPadPinFragment(
                FragmentType.PIN_ACCOUNT_PW.type
            ))
        }
    }
}