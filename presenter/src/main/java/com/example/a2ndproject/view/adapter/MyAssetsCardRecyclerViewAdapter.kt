package com.example.a2ndproject.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a2ndproject.databinding.RvItemMyAssetsBinding
import com.example.a2ndproject.view.fragment.MyAssetsCardFragment

class MyAssetsCardRecyclerViewAdapter(private val list: List<MyAssetsCardFragment.Card>) :
    RecyclerView.Adapter<MyAssetsCardRecyclerViewAdapter.ViewHolder>() {

    interface OnClickMyAssetsCardListener {
        fun onClick(v: View, position: Int)
    }

    private lateinit var onClickMyAssetsCardListener: OnClickMyAssetsCardListener

    fun setOnClickMyAssetsCardListener(listener: OnClickMyAssetsCardListener) {
        onClickMyAssetsCardListener = object : OnClickMyAssetsCardListener {
            override fun onClick(v: View, position: Int) {
                onClickMyAssetsCardListener = listener
            }
        }
    }

    inner class ViewHolder(private val binding: RvItemMyAssetsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun binding(data: MyAssetsCardFragment.Card) {
            binding.tvTitleRvItemMyAssets.text = data.title
            binding.tvSubtitleRvItemMyAssets.text = data.subtitle

            binding.cvRvItemMyAssets.setOnClickListener {
                onClickMyAssetsCardListener.onClick(it, adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RvItemMyAssetsBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding(list[position])
    }

    override fun getItemCount(): Int = list.size
}