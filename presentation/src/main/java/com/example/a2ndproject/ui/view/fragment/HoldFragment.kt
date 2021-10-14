package com.example.a2ndproject.ui.view.fragment

import com.example.a2ndproject.R
import com.example.a2ndproject.databinding.HoldFragmentBinding
import com.example.a2ndproject.ui.view.base.BaseFragment
import com.example.a2ndproject.ui.viewmodel.fragment.HoldViewModel

class HoldFragment : BaseFragment<HoldFragmentBinding>() {

    private lateinit var viewModel: HoldViewModel

    override fun getLayoutResId(): Int =
        R.layout.hold_fragment

}