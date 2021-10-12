package com.example.a2ndproject.ui.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.a2ndproject.R
import com.example.a2ndproject.databinding.HomeFragmentTabBinding
import com.example.a2ndproject.ui.view.activity.AddAccountActivity
import com.example.a2ndproject.ui.view.base.BaseFragment
import com.example.a2ndproject.ui.viewmodel.fragment.HomeViewModel

class HomeTabFragment : BaseFragment<HomeFragmentTabBinding>() {

    override fun getLayoutResId(): Int =
        R.layout.home_fragment_tab

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    // 프래그먼트를 하나로 돌려쓰기 때문에, position 값으로 뷰 내용을 변경해줌
    // [position] 0: 신한은행, 1: 오픈뱅킹
    fun setTab(position: Int) {
        // 신한은행으로 가입하는지, 오픈뱅킹 서비스로 가입하는 지 구별하기 위해 선언
        // [type] 0: 신한은행, 1: 오픈뱅킹
        val bundle = Bundle()

        when (position) {
            0 -> {
                binding.tvTitleTab.text = "간편한 입출금통장"
                binding.tvContentTab.text = "통장과 체크카드를 한번에!" // TODO 체크카드 API 없으면 삭제
                binding.btnTab.text = "가입하기"

                binding.btnTab.setOnClickListener {
                    bundle.putInt("type", 0)
                    intentToAddAccount(bundle)
                }
            }
            1 -> {
                binding.tvTitleTab.text = "오픈뱅킹 서비스"
                binding.tvContentTab.text = "신한은행 계좌 없이도 다른은행 계좌 조회, 이체를 신한 쏠에서 편하게!"
                binding.btnTab.text = "다른은행 계좌 등록하기"

                binding.btnTab.setOnClickListener {
                    bundle.putInt("type", 1)
                    intentToAddAccount(bundle)
                }
            }
        }
    }

    fun a() {
        val ex = true

        if (ex) {
            binding.layoutNoAccountTabVpItem.visibility = View.GONE
            binding.layoutAccountTabVpItem.visibility = View.VISIBLE
        } else {

        }
    }

    private fun intentToAddAccount(bundle: Bundle) {
        val intent = Intent(requireActivity(), AddAccountActivity::class.java)
        startActivity(intent, bundle)
    }

}
