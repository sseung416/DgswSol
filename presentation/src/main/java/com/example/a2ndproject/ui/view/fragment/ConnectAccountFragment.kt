package com.example.a2ndproject.ui.view.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.a2ndproject.R
import com.example.a2ndproject.databinding.ConnectAccountFragmentBinding
import com.example.a2ndproject.ui.view.base.BaseFragment

class ConnectAccountFragment : BaseFragment<ConnectAccountFragmentBinding>() {

    override fun getLayoutResId(): Int =
        R.layout.connect_account_fragment

    private lateinit var viewModel: ConnectAccountViewModel

}