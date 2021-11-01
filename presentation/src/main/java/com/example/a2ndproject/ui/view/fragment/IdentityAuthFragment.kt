package com.example.a2ndproject.ui.view.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.a2ndproject.R
import com.example.a2ndproject.databinding.AddAccountIdentityAuthFragmentBinding
import com.example.a2ndproject.ui.view.base.BaseFragment
import com.example.a2ndproject.ui.view.utils.MessageUtil
import com.example.a2ndproject.ui.view.utils.isNotBlankAll
import com.example.a2ndproject.ui.viewmodel.fragment.IdentityAuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IdentityAuthFragment : BaseFragment<AddAccountIdentityAuthFragmentBinding>() {

    private val viewModel: IdentityAuthViewModel by viewModels()

    private val navArgs by navArgs<IdentityAuthFragmentArgs>()

    override fun getLayoutResId(): Int =
        R.layout.add_account_identity_auth_fragment

    override fun setViewModel() { binding.vm = viewModel }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observe()
    }

    private fun observe() = with(viewModel) {
        isFailure.observe(viewLifecycleOwner) {
            MessageUtil.showDialog(requireActivity(), "알림", this@IdentityAuthFragment.getString(R.string.fail_server))
        }

        isSuccessCheckAccount.observe(viewLifecycleOwner) {
            navController.navigate(R.id.action_identityAuthFragment_to_checkInfoFragment)
        }

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
            val list = listOf(name.value, number.value, numberBack.value)

            btnEnabled.value = when {
                list.isNotBlankAll() && error.value.isNullOrBlank() -> true
                else -> false
            }
        })

    }
}