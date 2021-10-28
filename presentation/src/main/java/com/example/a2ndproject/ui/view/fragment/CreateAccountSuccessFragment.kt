package com.example.a2ndproject.ui.view.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.a2ndproject.R
import com.example.a2ndproject.databinding.AddAccountCreateSuccessFragmentBinding
import com.example.a2ndproject.ui.view.activity.MainActivity
import com.example.a2ndproject.ui.view.base.BaseFragment

class CreateAccountSuccessFragment : BaseFragment<AddAccountCreateSuccessFragmentBinding>() {

    override fun getLayoutResId(): Int =
        R.layout.add_account_create_success_fragment

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