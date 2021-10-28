package com.example.a2ndproject.ui.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.a2ndproject.R
import com.example.a2ndproject.databinding.HomeFragmentTabBinding
import com.example.a2ndproject.ui.view.activity.AddAccountActivity
import com.example.a2ndproject.ui.view.base.BaseFragment
import com.example.a2ndproject.ui.view.data.FragmentType
import com.example.a2ndproject.ui.viewmodel.fragment.HomeViewModel

class HomeTabFragment : BaseFragment<HomeFragmentTabBinding>() {

    private val viewModel: HomeViewModel by activityViewModels()

    override fun getLayoutResId(): Int =
        R.layout.home_fragment_tab

    override fun setViewModel() {
        binding.vm = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var type = 0
        viewModel.position.observe(viewLifecycleOwner) {
            type = when (it) {
                0 -> FragmentType.ADD_ACCOUNT_CREATE.type
                1 -> FragmentType.ADD_ACCOUNT_CONNECT.type
                else -> 0
            }
        }

        binding.btnTab.setOnClickListener {
            val intent = Intent(requireActivity(), AddAccountActivity::class.java)
            intent.putExtra("type", type)
            requireActivity().startActivity(intent)
        }
    }


}
