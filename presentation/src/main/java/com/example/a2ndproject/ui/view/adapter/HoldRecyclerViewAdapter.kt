package com.example.a2ndproject.ui.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.example.a2ndproject.databinding.LayoutItemHoldBinding
import com.example.domain.entity.response.Account

class HoldRecyclerViewAdapter : RecyclerView.Adapter<HoldRecyclerViewAdapter.ViewHolder>() {

    private val list: ArrayList<Account> = arrayListOf()

    private var lastCheckedPos: Int? = null
    private var lastChecked: CheckBox? = null

    inner class ViewHolder(
        private val binding: LayoutItemHoldBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(account: Account, position: Int) {
            binding.data = account

            binding.checkBox.apply {
                setOnClickListener {
                    if (isChecked) {
                        if (lastChecked != null) {
                            lastChecked!!.isChecked = false
                        }

                        lastChecked = this
                        if (lastCheckedPos != null) {
                            notifyItemChanged(lastCheckedPos!!)
                        }

                        lastCheckedPos = position
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = LayoutItemHoldBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position], position)
    }

    override fun getItemCount(): Int =
        list.size

    fun setList(list: List<Account>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    fun getReceiveAccountId(): String {
        return if (lastCheckedPos == null) {
            ""
        } else {
            list[lastCheckedPos!!].accountID
        }
    }
}