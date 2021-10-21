package com.example.a2ndproject.ui.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.example.a2ndproject.R
import com.example.a2ndproject.databinding.SignUpSecondFragmentBinding
import com.example.a2ndproject.ui.view.base.BaseFragment
import com.example.a2ndproject.ui.viewmodel.fragment.SignUpViewModel

class SignUpSecondFragment : BaseFragment<SignUpSecondFragmentBinding>() {

    private val viewModel: SignUpViewModel by activityViewModels()

    override fun setViewModel() {
        binding.vm = viewModel
    }

    override fun getLayoutResId(): Int =
        R.layout.sign_up_second_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel
    }
}