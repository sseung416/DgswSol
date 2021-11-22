package com.example.a2ndproject.ui.view.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.a2ndproject.R
import com.example.a2ndproject.ui.viewmodel.fragment.TransferCheckViewModel

class TransferCheckFragment : Fragment() {

    companion object {
        fun newInstance() = TransferCheckFragment()
    }

    private lateinit var viewModel: TransferCheckViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.transfer_check_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TransferCheckViewModel::class.java)
        // TODO: Use the ViewModel
    }

}