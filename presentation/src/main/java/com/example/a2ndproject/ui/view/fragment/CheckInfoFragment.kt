package com.example.a2ndproject.ui.view.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.a2ndproject.R
import com.example.a2ndproject.databinding.CreateAccountCheckInfoFragmentBinding
import com.example.a2ndproject.ui.view.base.BaseFragment
import com.example.a2ndproject.ui.view.data.FragmentType
import com.example.a2ndproject.ui.view.utils.MessageUtil
import com.example.a2ndproject.ui.viewmodel.fragment.CheckInfoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CheckInfoFragment : BaseFragment<CreateAccountCheckInfoFragmentBinding>() {

    private val viewModel: CheckInfoViewModel by viewModels()

    override fun getLayoutResId(): Int =
        R.layout.create_account_check_info_fragment

    override fun setViewModel() {
        binding.vm = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observe()

        viewModel.getProfile()

        binding.btnConfirmCheckInfo.setOnClickListener {
            if (binding.etCheckInfo.text!!.isNotBlank()) {
                navController.navigate(CheckInfoFragmentDirections.actionCheckInfoFragmentToNumberPadPinFragment(
                    FragmentType.PIN_ACCOUNT_PW.type, viewModel.accountName.value!!
                ))
            } else {
                MessageUtil.showDialog(requireActivity(), "별명을 입력해주세요.")
            }
        }
    }

    private fun observe() = with(viewModel) {
        isSuccess.observe(viewLifecycleOwner) {
            profile.value = it
        }

        isFailure.observe(viewLifecycleOwner) {
            MessageUtil.showDialog(requireActivity(), "서버 통신에 실패하였습니다.")
        }
    }
}