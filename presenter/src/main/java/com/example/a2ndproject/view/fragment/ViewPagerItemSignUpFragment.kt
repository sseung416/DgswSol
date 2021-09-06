package com.example.a2ndproject.view.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.a2ndproject.R
import com.example.a2ndproject.databinding.ViewPagerItemSignUpFragmentBinding
import com.example.a2ndproject.view.viewmodel.SignUpViewModel

class ViewPagerItemSignUpFragment : Fragment() {

    private lateinit var binding: ViewPagerItemSignUpFragmentBinding
    private lateinit var viewModel: SignUpViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.view_pager_item_sign_up_fragment, container, false)
        init()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun init() {
        viewModel = ViewModelProvider(requireActivity()).get(SignUpViewModel::class.java)
    }

    /**
     * ViewPager의 position이 바뀌었을 때 호출되어 view의 visibility를 변경
     *
     * @param position : ViewPager가 바뀌었을 때의 position
     *  */
    fun setLayout(position: Int) {
        /* 모든 view를 gone으로 바꾼 뒤 position에 해당하는 뷰만 visible로 변경함 */
        binding.constraintLayout1ViewPagerItemSignUp.visibility = View.GONE
        binding.constraintLayout2ViewPagerItemSignUp.visibility = View.GONE
        binding.constraintLayout3ViewPagerItemSignUp.visibility = View.GONE

        when (position) {
            0 -> binding.constraintLayout1ViewPagerItemSignUp.visibility = View.VISIBLE
            1 -> binding.constraintLayout2ViewPagerItemSignUp.visibility = View.VISIBLE
            2 -> binding.constraintLayout3ViewPagerItemSignUp.visibility = View.VISIBLE
        }
    }
}