package com.example.a2ndproject.ui.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.a2ndproject.R
import com.example.a2ndproject.databinding.CreateAccountFragmentBinding
import com.example.a2ndproject.ui.view.base.BaseFragment
import com.example.a2ndproject.ui.viewmodel.fragment.CreateAccountViewModel

class CreateAccountFragment : BaseFragment<CreateAccountFragmentBinding>() {

    override fun getLayoutResId(): Int =
        R.layout.create_account_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}