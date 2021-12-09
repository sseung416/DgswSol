package com.example.a2ndproject.ui.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a2ndproject.databinding.LayoutItemHomeTabBinding
import com.example.a2ndproject.ui.view.model.Home
import com.example.domain.entity.response.Account

class HomeTabRecyclerViewAdapter : RecyclerView.Adapter<HomeTabRecyclerViewAdapter.ViewHolder>() {

    interface OnClickHomeTabItemListener {
        fun onClick(account: Account)
    }

    private lateinit var setOnClickTransferListener: OnClickHomeTabItemListener
    private lateinit var setOnClickHoldListener: OnClickHomeTabItemListener

    fun setOnClickTransferListener(listener: (Account) -> Unit) {
        setOnClickTransferListener = object : OnClickHomeTabItemListener {
            override fun onClick(account: Account) {
                listener(account)
            }
        }
    }

    fun setOnClickHoldListener(listener: (Account) -> Unit) {
        setOnClickHoldListener = object : OnClickHomeTabItemListener {
            override fun onClick(account: Account) {
                listener(account)
            }
        }
    }

    private val list: ArrayList<Account> = arrayListOf()

    inner class ViewHolder(
        private val binding: LayoutItemHomeTabBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(account: Account) {
            binding.data = account

            binding.btnTransferLayoutHomeTab.setOnClickListener {
                setOnClickTransferListener.onClick(account)
            }

            binding.btnHoldLayoutHomeTab.setOnClickListener {
                setOnClickHoldListener.onClick(account)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = LayoutItemHomeTabBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int =
        list.size

    fun setList(list: List<Account>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }
}