package com.example.a2ndproject.view.fragment

import android.content.Intent
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
import com.example.a2ndproject.view.activity.AddAccountActivity
import com.example.a2ndproject.view.activity.MainActivity

class TabLayoutFragment : Fragment() {

    private lateinit var binding: TabLayoutFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.tab_layout_fragment, container, false)

        return binding.root
    }

    fun setTab(position: Int) {
        var bundle = Bundle()

        when (position) {
            0 -> {
                binding.tvTitleTab.text = "간편한 입출금통장"
                binding.tvContentTab.text = "통장과 체크카드를 한번에!" // TODO 체크카드 API 없으면 지우기
                binding.btnTab.text = "가입하기"

                binding.btnTab.setOnClickListener {
                    bundle.putInt("type", 0)
                    intentToAddAccount(bundle)
                }
            }

            1 -> {
                binding.tvTitleTab.text = "오픈뱅킹 서비스"
                binding.tvContentTab.text = "신한은행 계좌 없이도 다른은행 계좌 조회, 이체를 [앱이름]에서 편하게!"
                binding.btnTab.text = "다른은행 계좌 등록하기"

                binding.btnTab.setOnClickListener {
                    bundle.putInt("type", 1)
                    intentToAddAccount(bundle)
                }
            }
        }
    }

    private fun intentToAddAccount(bundle: Bundle) {
        val intent = Intent(requireActivity(), AddAccountActivity::class.java)
        startActivity(intent, bundle)
    }
}