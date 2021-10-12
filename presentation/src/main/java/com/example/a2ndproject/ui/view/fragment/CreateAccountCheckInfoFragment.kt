package com.example.a2ndproject.ui.view.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.a2ndproject.R
import com.example.a2ndproject.databinding.CreateAccountCheckInfoFragmentBinding
import com.example.a2ndproject.ui.view.base.BaseFragment
import com.example.a2ndproject.ui.viewmodel.fragment.CreateAccountCheckInfoViewModel

class CreateAccountCheckInfoFragment : BaseFragment<CreateAccountCheckInfoFragmentBinding>() {

    private lateinit var viewModel: CreateAccountCheckInfoViewModel

    override fun getLayoutResId(): Int =
        R.layout.create_account_check_info_fragment

}