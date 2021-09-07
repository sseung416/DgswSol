/**
 * 회원가입의 ViewPager를 연결할 Adapter Class
 * View Binding 사용
 *
 * @author 최승연
 * @date 2021-09-07
 * */
package com.example.a2ndproject.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a2ndproject.databinding.ViewPagerItemSignUpFragmentBinding

class SignUpViewPagerAdapter : RecyclerView.Adapter<SignUpViewPagerAdapter.ViewHolder>() {
    private lateinit var binding: ViewPagerItemSignUpFragmentBinding

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {

        /**
         * view의 visibility 변경
         *
         * @param position : ViewPager의 position
         *  */
        fun setLayout(position: Int) {
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ViewPagerItemSignUpFragmentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setLayout(position)
    }

    override fun getItemCount(): Int = 3


}
