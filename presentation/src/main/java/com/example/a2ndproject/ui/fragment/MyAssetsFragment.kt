package com.example.a2ndproject.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.a2ndproject.R
import com.example.a2ndproject.databinding.MyAssetsFragmentBinding
import com.example.a2ndproject.ui.viewmodel.MyAssetsViewModel

class MyAssetsFragment : Fragment() {

    private lateinit var binding: MyAssetsFragmentBinding
    private lateinit var viewModel: MyAssetsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.my_assets_fragment, container, false)

        init()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setFragment()
    }

    private fun init() {
        viewModel = ViewModelProvider(requireActivity()).get(MyAssetsViewModel::class.java)
    }

    private fun setFragment() {
        // TODO replace 하면서 값 전달하기
        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainerView1_my_assets, MyAssetsCardFragment())
            .commit()

        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainerView2_my_assets, MyAssetsCardFragment())
            .commit()
    }
}