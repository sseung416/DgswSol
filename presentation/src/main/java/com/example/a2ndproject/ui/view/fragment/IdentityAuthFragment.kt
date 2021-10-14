package com.example.a2ndproject.ui.view.fragment

import android.os.Bundle
import android.view.View
import com.example.a2ndproject.R
import com.example.a2ndproject.databinding.AddAccountIdentityAuthFragmentBinding
import com.example.a2ndproject.ui.view.base.BaseFragment

class IdentityAuthFragment : BaseFragment<AddAccountIdentityAuthFragmentBinding>() {

    override fun getLayoutResId(): Int =
        R.layout.add_account_identity_auth_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}