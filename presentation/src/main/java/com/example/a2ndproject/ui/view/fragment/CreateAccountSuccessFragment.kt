package com.example.a2ndproject.ui.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.a2ndproject.R
import com.example.a2ndproject.databinding.CreateAccountSuccessFragmentBinding
import com.example.a2ndproject.ui.view.activity.MainActivity
import com.example.a2ndproject.ui.view.base.BaseFragment

class CreateAccountSuccessFragment : BaseFragment<CreateAccountSuccessFragmentBinding>() {

    override fun getLayoutResId(): Int =
        R.layout.create_account_success_fragment

    override fun setViewModel() {}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnConfirmCreateSuccess.setOnClickListener {
            navigateToMain()
        }
    }

    private fun navigateToMain() {
        requireActivity().startActivity(Intent(requireActivity(), MainActivity::class.java))
    }
}