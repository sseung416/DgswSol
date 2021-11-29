package com.example.a2ndproject.ui.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a2ndproject.databinding.RvItemConnectAccountBinding
import com.example.a2ndproject.ui.view.model.Connect

class ConnectRecyclerViewAdapter : RecyclerView.Adapter<ConnectRecyclerViewAdapter.ViewHolder>() {

    private val list: ArrayList<Connect> = arrayListOf()

    class ViewHolder(
        private val binding: RvItemConnectAccountBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Connect) {
            binding.data = data
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RvItemConnectAccountBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int =
        list.size

    fun setList(list: List<Connect>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }
}