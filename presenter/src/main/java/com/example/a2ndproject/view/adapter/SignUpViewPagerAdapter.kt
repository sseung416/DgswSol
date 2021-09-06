/**
 * 회원가입의 ViewPager를 연결할 Adapter Class
 *
 * @author 최승연
 * @date 2021-09-06
 * */
package com.example.a2ndproject.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentContainerView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.a2ndproject.R
import com.example.a2ndproject.databinding.ViewPagerItemSignUpFragmentBinding

class SignUpViewPagerAdapter : RecyclerView.Adapter<SignUpViewPagerAdapter.ViewHolder>() {
    private lateinit var binding: ViewPagerItemSignUpFragmentBinding

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ViewPagerItemSignUpFragmentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        setLayout(position)
    }

    override fun getItemCount(): Int = 3

    /**
     * ViewPager의 position이 바뀌었을 때 호출되어 view의 visibility를 변경
     *
     * @param position : ViewPager가 바뀌었을 때의 position
     *  */
    private fun setLayout(position: Int) {
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
