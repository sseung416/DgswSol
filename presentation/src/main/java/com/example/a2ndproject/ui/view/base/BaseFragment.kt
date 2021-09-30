/***/
package com.example.a2ndproject.ui.view.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController

abstract class BaseFragment<B: ViewDataBinding, V: BaseViewModel> : Fragment() {

    protected val TAG: String = javaClass.simpleName

    protected lateinit var binding: B
    protected lateinit var viewModel: V

    protected val navController: NavController by lazy {
        findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(requireActivity()).get(getViewModelClass())
        binding = getFragmentBinding(inflater, container)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    /**
     * 프래그먼트 바인딩을 하는 메서드
     * */
    abstract fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): B

    abstract fun getViewModelClass(): Class<V>

}