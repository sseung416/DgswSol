package com.example.a2ndproject.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.adapters.TableLayoutBindingAdapter
import com.example.a2ndproject.R
import com.example.a2ndproject.databinding.TabLayoutFragmentBinding

class TabLayoutFragment : Fragment() {

    private lateinit var binding: TabLayoutFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("TabLayoutFragment", "onCreateView")
        binding = DataBindingUtil.inflate(inflater, R.layout.tab_layout_fragment, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    fun setTab(position: Int) {
        when (position) {
            0 -> {
                binding.tvTitleTab.text = "간편한 입출금통장"
                binding.tvContentTab.text = "통장과 체크카드를 한번에!" // TODO 체크카드 API 없으면 지우기
                binding.btnTab.text = "가입하기"
            }

            1 -> {
                binding.tvTitleTab.text = "오픈뱅킹 서비스"
                binding.tvContentTab.text = "신한은행 계좌 없이도 다른은행 계좌 조회, 이체를 00에서 편하게!"
                binding.btnTab.text = "다른은행 계좌 등록하기"
            }
        }
    }
}