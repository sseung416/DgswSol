package com.example.a2ndproject.ui.view.fragment

import android.os.Bundle
import android.view.View
import com.example.a2ndproject.R
import com.example.a2ndproject.databinding.AddAccountCheckInfoFragmentBinding
import com.example.a2ndproject.ui.view.base.BaseFragment
import com.example.a2ndproject.ui.viewmodel.fragment.CheckInfoViewModel

class CheckInfoFragment : BaseFragment<AddAccountCheckInfoFragmentBinding>() {

    private lateinit var viewModel: CheckInfoViewModel

    override fun getLayoutResId(): Int =
        R.layout.add_account_check_info_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observe()
    }

    private fun observe() = with(viewModel) {
        navigateToPin.observe(viewLifecycleOwner, {
            navController.navigate(R.id.action_checkInfoFragment_to_numberPadPinFragment)
        })
    }
}