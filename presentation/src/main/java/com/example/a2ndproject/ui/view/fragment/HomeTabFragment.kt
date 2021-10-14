package com.example.a2ndproject.ui.view.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.a2ndproject.R
import com.example.a2ndproject.databinding.HomeFragmentTabBinding
import com.example.a2ndproject.ui.view.base.BaseFragment
import com.example.a2ndproject.ui.viewmodel.fragment.HomeViewModel

class HomeTabFragment : BaseFragment<HomeFragmentTabBinding>() {

    private val viewModel: HomeViewModel by activityViewModels()

    override fun getLayoutResId(): Int =
        R.layout.home_fragment_tab

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.vm = viewModel

        observe()
    }

    private fun observe() = with(viewModel) {
        navigateAddAccount.observe(viewLifecycleOwner, {
            navController.navigate(R.id.action_homeFragment_to_addAccountFragment)
        })
    }


}
