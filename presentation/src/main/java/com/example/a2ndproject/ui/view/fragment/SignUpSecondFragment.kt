package com.example.a2ndproject.ui.view.fragment

import android.os.Bundle
import android.telephony.PhoneNumberUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.example.a2ndproject.R
import com.example.a2ndproject.databinding.SignUpSecondFragmentBinding
import com.example.a2ndproject.ui.view.base.BaseFragment
import com.example.a2ndproject.ui.view.utils.getEmptyErrorString
import com.example.a2ndproject.ui.view.utils.getNotRegularErrorString
import com.example.a2ndproject.ui.viewmodel.fragment.SignUpViewModel
import java.util.*

class SignUpSecondFragment : BaseFragment<SignUpSecondFragmentBinding>() {

    private val viewModel: SignUpViewModel by activityViewModels()

    override fun setViewModel() {
        binding.vm = viewModel
    }

    override fun getLayoutResId(): Int =
        R.layout.sign_up_second_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.etPhoneNumberSignUpSecond.requestFocus()

        binding.scrollView.setOnTouchListener { _, _ -> return@setOnTouchListener true }

        observe()

        binding.etPhoneNumberSignUpSecond.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                binding.motionSignUpSecond.transitionToState(R.id.startToMiddle)
                binding.etResidentNumberSignUpSecond.requestFocus()
            }
            return@setOnEditorActionListener true
        }

        binding.etResidentNumberSignUpSecond.setOnEditorActionListener { _, id, _ ->
            if (id == EditorInfo.IME_ACTION_NEXT) {
                binding.etResidentNumberBackSignUpSecond.requestFocus()
            }
            return@setOnEditorActionListener true
        }

        binding.etResidentNumberBackSignUpSecond.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                if (viewModel.phoneNumErr.value.isNullOrBlank() && viewModel.residentNumber.value.isNullOrBlank()) {
                    binding.motionSignUpSecond.transitionToState(R.id.middleToEnd)
                    binding.etNameSignUpSecond.requestFocus()
                }
            }
            return@setOnEditorActionListener true
        }

        binding.etNameSignUpSecond.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_NEXT){
                navController.navigate(R.id.action_signUpSecondFragment_to_signUpThirdFragment)

                if (viewModel.phoneNumErr.value.isNullOrBlank() && viewModel.residentNumber.value.isNullOrBlank() && viewModel.nameErr.value.isNullOrBlank()) {
                }
            }
            return@setOnEditorActionListener true
        }
    }

    private fun observe() = with(viewModel) {
        /**
         * currentItem 영업 안 합니다
         * */
        currentItem.observe(viewLifecycleOwner) {
            when (it) {
                3 -> {
                    binding.motionSignUpSecond.transitionToState(R.id.startToMiddle)
                    binding.etResidentNumberSignUpSecond.requestFocus()
                }

                4 -> {
                    binding.motionSignUpSecond.transitionToState(R.id.middleToEnd)
                    binding.etNameSignUpSecond.requestFocus()
                }

                5 -> navController.navigate(R.id.action_signUpSecondFragment_to_signUpThirdFragment)
            }
        }

        phoneNumber.observe(viewLifecycleOwner) {
            phoneNumErr.value = when {
                it == null -> ""

                it.isEmpty() -> getEmptyErrorString("휴대폰 번호를")

                it.length != 10 -> getNotRegularErrorString("휴대폰 번호의")

                else -> ""
            }

            errorMsg.value = phoneNumErr.value
        }

        residentNumber.observe(viewLifecycleOwner) {
            residentNumErr.value = when {
                it == null -> ""

                it.isBlank() -> getEmptyErrorString("주민등록번호를")

                else -> ""
            }

            errorMsg.value = residentNumErr.value
        }

        residentNumberBack.observe(viewLifecycleOwner) {
            residentNumErr.value = when {
                it == null -> {
                    ""
                }

                it.isBlank() -> getEmptyErrorString("주민등록번호를")

                else -> ""
            }

            errorMsg.value = residentNumErr.value
        }

        name.observe(viewLifecycleOwner) {
            nameErr.value = when {
                it == null -> ""

                it.isBlank() -> getEmptyErrorString("이름을")

                else -> ""
            }

            errorMsg.value = nameErr.value
        }

//        errorMsg.observe(viewLifecycleOwner) {
//            if (it.isNullOrBlank()) {
//                errorMsg.value = when {
//                    phoneNumErr.value!!.isNotBlank() -> phoneNumErr.value
//                    residentNumErr.value!!.isNotBlank() -> residentNumErr.value
//                    nameErr.value!!.isNotBlank() -> nameErr.value
//                    else -> ""
//                }
//            }
//        }
    }
}