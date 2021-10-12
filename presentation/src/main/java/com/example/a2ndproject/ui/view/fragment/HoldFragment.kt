package com.example.a2ndproject.ui.view.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.a2ndproject.R
import com.example.a2ndproject.databinding.HoldFragmentBinding
import com.example.a2ndproject.ui.view.base.BaseFragment
import com.example.a2ndproject.ui.viewmodel.HoldViewModel

class HoldFragment : BaseFragment<HoldFragmentBinding>() {

    private lateinit var viewModel: HoldViewModel

    override fun getLayoutResId(): Int =
        R.layout.hold_fragment

}