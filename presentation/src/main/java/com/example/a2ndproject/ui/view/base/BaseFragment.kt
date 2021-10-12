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

    protected lateinit var binding: B

    protected val navController: NavController by lazy {
        findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, getLayoutResId(), container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
    }

//    override fun onDestroyView() {
//        super.onDestroyView()
//        binding = null
//    }

    protected abstract fun getLayoutResId(): Int

    // todo moved to utils
    protected fun showToast(msg: String) =
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()

    protected fun showLog(msg: String) =
        Log.d(TAG, msg)
}