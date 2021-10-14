package com.example.a2ndproject.ui.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.a2ndproject.R
import com.example.a2ndproject.databinding.AddAccountCreateSuccessFragmentBinding
import com.example.a2ndproject.ui.view.base.BaseFragment

class CreateAccountSuccessFragment : BaseFragment<AddAccountCreateSuccessFragmentBinding>() {

    override fun getLayoutResId(): Int =
        R.layout.add_account_create_success_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnConfirmCreateSuccess.setOnClickListener {
            navController.navigate(R.id.action_createAccountSuccessFragment_to_homeFragment)
        }
    }
}