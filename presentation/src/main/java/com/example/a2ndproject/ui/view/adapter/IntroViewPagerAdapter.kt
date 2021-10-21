/**
 * IntroFragment의 ViewPager를 연결하기 위한 Adapter Class
 *
 * @author 최승연
 * @date 2021-09-06
 * */
package com.example.a2ndproject.ui.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.a2ndproject.R

class IntroViewPagerAdapter(private val list: List<String>) : RecyclerView.Adapter<IntroViewPagerAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tv1: TextView = itemView.findViewById(R.id.tv1_intro_view_pager_item)
        val tv2: TextView = itemView.findViewById(R.id.tv2_intro_view_pager_item)
        val img: ImageView = itemView.findViewById(R.id.iv_intro_view_pager_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflate = LayoutInflater.from(parent.context).inflate(R.layout.vp_item_intro_fragment, parent, false)
        return ViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = list[position]

        holder.tv1.text = data
    }

    override fun getItemCount(): Int = list.size
}