/**
 * 회원가입의 ViewPager를 연결할 어댑터 클래스입니다.
 *
 * @author 최승연
 * @date 2021-09-06
 * */
package com.example.a2ndproject.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a2ndproject.R

class SignUpViewPagerAdapter : RecyclerView.Adapter<SignUpViewPagerAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflated = LayoutInflater.from(parent.context).inflate(R.layout.view_pager_item_sign_up_fragment, parent, false)
        return ViewHolder(inflated)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    }

    override fun getItemCount(): Int = 3
}