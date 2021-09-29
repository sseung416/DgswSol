package com.example.a2ndproject.ui.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.a2ndproject.R
import com.example.a2ndproject.databinding.CreateAccountFragmentBinding
import com.example.a2ndproject.ui.base.BaseFragment

class CreateAccountFragment : BaseFragment<CreateAccountFragmentBinding, CreateAccountViewModel>() {

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): CreateAccountFragmentBinding =
        CreateAccountFragmentBinding.inflate(inflater, container, false)

    override fun getViewModelClass(): Class<CreateAccountViewModel> = CreateAccountViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}