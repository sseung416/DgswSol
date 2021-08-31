package com.example.a2ndproject.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.a2ndproject.R
import com.example.a2ndproject.databinding.PinNumberFragmentBinding
import com.example.a2ndproject.view.viewmodel.PinNumberViewModel

class PinNumberFragment : Fragment() {

    private lateinit var binding: PinNumberFragmentBinding
    private lateinit var viewModel: PinNumberViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.pin_number_fragment, container, false)

        init()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    private fun init() {
        viewModel = ViewModelProvider(requireActivity()).get(PinNumberViewModel::class.java)
    }

    private fun setBtn() {
        binding.btn0PinNumber.setOnClickListener {

        }

        binding.btn1PinNumber.setOnClickListener {

        }

        binding.btn2PinNumber.setOnClickListener {

        }

        binding.btn3PinNumber.setOnClickListener {

        }

        binding.btn4PinNumber.setOnClickListener {

        }

        binding.btn5PinNumber.setOnClickListener {

        }

        binding.btn6PinNumber.setOnClickListener {

        }


    }

    private fun fillPin() {

    }
}