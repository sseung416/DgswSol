package com.example.a2ndproject.ui.view.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.a2ndproject.R
import com.example.a2ndproject.databinding.TransferCheckFragmentBinding
import com.example.a2ndproject.ui.view.base.BaseFragment
import com.example.a2ndproject.ui.viewmodel.fragment.TransferCheckViewModel

class TransferCheckFragment : BaseFragment<TransferCheckFragmentBinding>() {

    override fun getLayoutResId(): Int =
        R.layout.transfer_check_fragment

    override fun setViewModel() {}

    private val viewModel: TransferCheckViewModel by viewModels()
}
