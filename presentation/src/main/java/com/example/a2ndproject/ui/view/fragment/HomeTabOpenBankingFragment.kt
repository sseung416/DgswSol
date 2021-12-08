package com.example.a2ndproject.ui.view.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.a2ndproject.R
import com.example.a2ndproject.databinding.HomeTabOpenBankingFragmentBinding
import com.example.a2ndproject.ui.view.activity.ConnectAccountActivity
import com.example.a2ndproject.ui.view.base.BaseFragment
import com.example.a2ndproject.ui.view.model.Connect
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeTabOpenBankingFragment : BaseFragment<HomeTabOpenBankingFragmentBinding>() {

    override fun getLayoutResId(): Int =
        R.layout.home_tab_open_banking_fragment

    override fun setViewModel() {}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnTab.setOnClickListener {
            startActivity(Intent(requireActivity(), ConnectAccountActivity::class.java))
        }
    }
}