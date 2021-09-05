/**
 * 회원가입 프래그먼트
 *
 * @author 최승연
 * @date 2021-09-06
 * */
package com.example.a2ndproject.view.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.a2ndproject.R
import com.example.a2ndproject.databinding.SignUpFragmentBinding
import com.example.a2ndproject.view.viewmodel.SignUpViewModel

class SignUpFragment : Fragment() {

    private val navController by lazy {
        findNavController()
    }

    private lateinit var binding: SignUpFragmentBinding
    private lateinit var viewModel: SignUpViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.sign_up_fragment, container, false)

        init()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnConfirmSignUp.setOnClickListener {
            navigateToPinNumber()
        }

    }

    private fun init() {
        viewModel = ViewModelProvider(requireActivity()).get(SignUpViewModel::class.java)
    }

    private fun navigateToPinNumber() {
        val bundle = Bundle()
        bundle.putInt("type", 0)

        navController.navigate(R.id.action_signUpFragment_to_pinNuberFragment, bundle)
    }
}