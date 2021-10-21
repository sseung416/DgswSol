package com.example.a2ndproject.ui.view.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.a2ndproject.R
import com.example.a2ndproject.databinding.AddAccountIdentityAuthFragmentBinding
import com.example.a2ndproject.ui.view.base.BaseFragment
import com.example.a2ndproject.ui.viewmodel.fragment.IdentityAuthViewModel

class IdentityAuthFragment : BaseFragment<AddAccountIdentityAuthFragmentBinding>() {

    private val viewModel: IdentityAuthViewModel by viewModels()

    override fun getLayoutResId(): Int =
        R.layout.add_account_identity_auth_fragment

    override fun setViewModel() {
        binding.vm = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.vm = viewModel

        observe()
    }

    private fun observe() = with(viewModel) {
        name.observe(viewLifecycleOwner, {
            error.value = when (isErrorBlank()) {
                true -> resources.getString(R.string.error_input_info)

                false -> ""
            }
        })

        number.observe(viewLifecycleOwner, {
            error.value = when {
                isErrorBlank() -> resources.getString(R.string.error_input_info)

                error.value!!.length != 6 -> resources.getString(R.string.error_not_resident_number)

                else -> ""
            }
        })

        numberBack.observe(viewLifecycleOwner, {
            error.value = when {
                isErrorBlank() -> resources.getString(R.string.error_input_info)

                error.value!!.length != 1 -> resources.getString(R.string.error_not_resident_number)

                else -> ""
            }
        })

        error.observe(viewLifecycleOwner, {
            btnEnabled.value = when {
                name.value!!.isNotBlank() &&
                        number.value!!.isNotBlank() &&
                        numberBack.value!!.isNotBlank() &&
                        error.value!!.isBlank() ->
                    true

                else -> false
            }
        })

        navigateCheckInfo.observe(viewLifecycleOwner, {
            navController.navigate(R.id.action_identityAuthFragment_to_checkInfoFragment)
        })
    }
}