package com.example.a2ndproject.ui.view.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.a2ndproject.R
import com.example.a2ndproject.databinding.AddAccountIdentityAuthFragmentBinding
import com.example.a2ndproject.ui.view.base.BaseFragment
import com.example.a2ndproject.ui.view.utils.MessageUtil
import com.example.a2ndproject.ui.viewmodel.fragment.IdentityAuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IdentityAuthFragment : BaseFragment<AddAccountIdentityAuthFragmentBinding>() {

    private val viewModel: IdentityAuthViewModel by viewModels()

    override fun getLayoutResId(): Int =
        R.layout.add_account_identity_auth_fragment

    override fun setViewModel() { binding.vm = viewModel }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observe()

        binding.btnConfirmCreateAccount.setOnClickListener {
            viewModel.postCheckAccount()
        }
    }

    private fun observe() = with(viewModel) {
        isFailureCheck.observe(viewLifecycleOwner) {
            when (it) {
                null ->
                    MessageUtil.showDialog(requireActivity(), "알림", this@IdentityAuthFragment.getString(R.string.fail_server))

                else -> Toast.makeText(requireContext(), "정보 틀림", Toast.LENGTH_SHORT).show()
            }
        }

        isSuccessCheck.observe(viewLifecycleOwner) {
            if (navController.graph.id == R.id.nav_graph_crete_account) {
                navController.navigate(R.id.action_identityAuthFragment_to_checkInfoFragment)
            } else {
                // todo 계좌 연결할 때
            }
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

        })
    }

    private fun navigateToCheckInfo() {
        navController.navigate(R.id.action_identityAuthFragment_to_checkInfoFragment)
    }

    private fun navigateToConnect() {

    }
}