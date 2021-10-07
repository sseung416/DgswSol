package com.example.a2ndproject.ui.view.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.a2ndproject.R

class CreateAccountCheckInfoFragment : Fragment() {

    companion object {
        fun newInstance() = CreateAccountCheckInfoFragment()
    }

    private lateinit var viewModel: CreateAccountCheckInfoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.create_account_check_info_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CreateAccountCheckInfoViewModel::class.java)
        // TODO: Use the ViewModel
    }

}