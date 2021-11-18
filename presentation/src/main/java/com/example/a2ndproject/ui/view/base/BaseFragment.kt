package com.example.a2ndproject.ui.view.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import retrofit2.http.GET

abstract class BaseFragment<B: ViewDataBinding> : Fragment() {

    protected val TAG: String = javaClass.simpleName

    private lateinit var _binding: B
    protected val binding get() = _binding

    protected val navController: NavController by lazy {
        findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, getLayoutResId(), container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        setViewModel()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
//        _binding = null
    }

    protected abstract fun getLayoutResId(): Int

    protected abstract fun setViewModel()
}