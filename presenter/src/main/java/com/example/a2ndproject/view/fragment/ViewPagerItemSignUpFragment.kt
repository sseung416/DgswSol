package com.example.a2ndproject.view.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.a2ndproject.R
import com.example.a2ndproject.databinding.ViewPagerItemSignUpFragmentBinding
import com.example.a2ndproject.view.viewmodel.SignUpViewModel

class ViewPagerItemSignUpFragment : Fragment() {

    private lateinit var binding: ViewPagerItemSignUpFragmentBinding
    private lateinit var viewModel: SignUpViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("ViewPagerItem", "onCreateView")
        binding = DataBindingUtil.inflate(inflater, R.layout.view_pager_item_sign_up_fragment, container, false)
        init()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun init() {
        viewModel = ViewModelProvider(requireActivity()).get(SignUpViewModel::class.java)
    }

}