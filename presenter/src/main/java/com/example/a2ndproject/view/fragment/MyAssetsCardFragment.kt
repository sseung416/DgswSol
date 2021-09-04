package com.example.a2ndproject.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.a2ndproject.R
import com.example.a2ndproject.databinding.MyAssetsCardFragmentBinding
import com.example.a2ndproject.view.adapter.MyAssetsCardRecyclerViewAdapter

class MyAssetsCardFragment : Fragment() {

    private lateinit var binding: MyAssetsCardFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.my_assets_card_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView(0)
    }

    private fun initRecyclerView(type: Int) {
        val list = when (type) {
            0 -> {
                listOf(
                    Card("투자11", "부제목11", 123),
                    Card("투자22", "부제목22", 123)
                )
            }
            1 -> {
                listOf(
                    Card("목표11", "부제목11", 123),
                    Card("목표22", "부제목22", 123)
                )
            }
            else -> null
        }

        binding.rvMyAssetsItem.adapter = MyAssetsCardRecyclerViewAdapter(list!!)
    }

    // TODO 무지성 코드 클린 아키텍처 형식으로 고치기
    data class Card (
        val title: String,
        val subtitle: String?,
        val img: Int
    )
}