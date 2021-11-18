package com.example.a2ndproject.ui.view.fragment

import android.os.Bundle
import android.view.View
import com.example.a2ndproject.R
import com.example.a2ndproject.databinding.CreateAccountCheckInfoFragmentBinding
import com.example.a2ndproject.ui.view.base.BaseFragment
import com.example.a2ndproject.ui.view.data.FragmentType
import com.example.a2ndproject.ui.view.utils.MessageUtil

class CheckInfoFragment : BaseFragment<CreateAccountCheckInfoFragmentBinding>() {

    override fun getLayoutResId(): Int =
        R.layout.create_account_check_info_fragment

    override fun setViewModel() {}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnConfirmCheckInfo.setOnClickListener {
            if (binding.etCheckInfo.text!!.isNotBlank()) {
                navController.navigate(CheckInfoFragmentDirections.actionCheckInfoFragmentToNumberPadPinFragment(
                    FragmentType.PIN_ACCOUNT_PW.type, binding.etCheckInfo.text.toString()
                ))
            } else {
                MessageUtil.showDialog(requireActivity(), "별명을 입력해주세요.")
            }
        }
    }
}