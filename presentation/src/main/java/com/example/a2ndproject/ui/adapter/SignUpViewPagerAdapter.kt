/**
 * 회원가입의 ViewPager를 연결할 Adapter Class
 * View Binding 사용
 *
 * @author 최승연
 * @date 2021-09-07
 * */
package com.example.a2ndproject.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a2ndproject.databinding.ViewPagerItemSignUpFragmentBinding

class SignUpViewPagerAdapter : RecyclerView.Adapter<SignUpViewPagerAdapter.ViewHolder>() {

    interface OnClickSignUpListener {
        fun onClick()
    }

    private lateinit var setOnClickSignUpIdListener: OnClickSignUpListener
    private lateinit var setOnClickSignUpPasswordListener: OnClickSignUpListener

    fun setOnClickSignUpIdListener(listener: () -> Unit) {
        setOnClickSignUpIdListener = object : OnClickSignUpListener {
            override fun onClick() {
                listener()
            }
        }
    }

    fun setOnClickSignUpPasswordListener(listener: () -> Unit) {
        setOnClickSignUpPasswordListener = object : OnClickSignUpListener {
            override fun onClick() {
                listener()
            }
        }
    }

    lateinit var binding: ViewPagerItemSignUpFragmentBinding

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {

        /**
         * position에 따라 view의 visibility 변경하는 메서드
         *
         * @param position : ViewPager의 position
         *  */
        fun setLayout(position: Int) {
            /* 모든 view의 visibility를 gone으로 바꾸고 해당하는 position의 view만 visible로 바꿈 */
            binding.constraintLayout1ViewPagerItemSignUp.visibility = View.GONE
            binding.constraintLayout2ViewPagerItemSignUp.visibility = View.GONE
            binding.constraintLayout3ViewPagerItemSignUp.visibility = View.GONE

            when (position) {
                /* 아이디, 비밀번호 설정 */
                0 -> binding.constraintLayout1ViewPagerItemSignUp.visibility = View.VISIBLE

                /* 주민등록번호(7자리), 전화번호, 실명 설정 */
                1 -> binding.constraintLayout2ViewPagerItemSignUp.visibility = View.VISIBLE

                /* 프로필 사진, 별명 설정 */
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

        binding.btnIdDoubleCheckViewPagerItemSignUp.setOnClickListener {
            setOnClickSignUpIdListener.onClick()
        }

        binding.btnPasswordDoubleCheckViewPagerItemSignUp.setOnClickListener {
            setOnClickSignUpPasswordListener.onClick()
        }
    }

    override fun getItemCount(): Int = 3


}
