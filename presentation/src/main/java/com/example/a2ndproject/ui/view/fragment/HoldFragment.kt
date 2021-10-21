package com.example.a2ndproject.ui.view.fragment

import androidx.fragment.app.activityViewModels
import com.example.a2ndproject.R
import com.example.a2ndproject.databinding.HoldFragmentBinding
import com.example.a2ndproject.ui.view.base.BaseFragment
import com.example.a2ndproject.ui.viewmodel.fragment.HoldViewModel

class HoldFragment : BaseFragment<HoldFragmentBinding>() {

    private val viewModel: HoldViewModel by activityViewModels()

    override fun getLayoutResId(): Int =
        R.layout.hold_fragment

    override fun setViewModel() {
    }

}