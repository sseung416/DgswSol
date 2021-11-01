package com.example.a2ndproject.ui.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.example.a2ndproject.R
import com.example.a2ndproject.databinding.SignUpSecondFragmentBinding
import com.example.a2ndproject.ui.view.base.BaseFragment
import com.example.a2ndproject.ui.view.utils.getEmptyErrorString
import com.example.a2ndproject.ui.viewmodel.fragment.SignUpViewModel

class SignUpSecondFragment : BaseFragment<SignUpSecondFragmentBinding>() {

    private val viewModel: SignUpViewModel by activityViewModels()

    override fun setViewModel() {
        binding.vm = viewModel
    }

    override fun getLayoutResId(): Int =
        R.layout.sign_up_second_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observe()
    }

    private fun observe() = with(viewModel) {
        currentItem.observe(viewLifecycleOwner) {
            if (it == 6)
                navController.navigate(R.id.action_signUpSecondFragment_to_signUpThirdFragment)
        }

        phoneNumber.observe(viewLifecycleOwner) {
            val numLength = it.replace("-", "").length

            if (numLength == 10 || numLength == 11) {
                currentItem.value = 3
            }
        }

        residentNumber.observe(viewLifecycleOwner) {
            if (it.length == 6 && residentNumberBack.value!!.length == 1) {
                currentItem.value = 4
            } else {
                errorMsg.value = this@SignUpSecondFragment.getEmptyErrorString("주민등록번호")
            }
        }

        name.observe(viewLifecycleOwner) {
            if (it.isEmpty()) {
                errorMsg.value = this@SignUpSecondFragment.getEmptyErrorString("이름을")
            } else {
                currentItem.value = 5
            }
        }
    }
}