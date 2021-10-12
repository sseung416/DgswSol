/**
 * 숫자 패드 프래그먼트
 *
 * @author 최승연
 * @date 2021-09-08
 * */

package com.example.a2ndproject.ui.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.a2ndproject.R
import com.example.a2ndproject.databinding.NumberPadFragmentBinding
import com.example.a2ndproject.ui.view.base.BaseFragment
import com.example.a2ndproject.ui.viewmodel.fragment.NumberPadViewModel

class NumberPadFragment : BaseFragment<NumberPadFragmentBinding>() {

    private val viewModel: NumberPadViewModel by viewModels()

    private lateinit var keyButtonList: List<Button>

    override fun getLayoutResId(): Int =
        R.layout.number_pad_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        keyButtonList = getButtons(view)

        // list의 view의 클릭 리스너를 설정함
        keyButtonList.forEach {
            it.setOnClickListener { v ->
                /* resource id로 resource name 구하고, name에서 숫자만 추출해 pinNumber에 저장함. */
                val number = v.resources.getResourceEntryName(v.id).replace(("[^0-9]").toRegex(), "").toInt()
                viewModel.addNumber(number)
                Log.d("pinNumber", "click: $number")
            }
        }

        binding.btnDeletePinNumber.setOnClickListener {
            viewModel.removeNumber()
        }
    }

    /**
     * numberButtons list에 string값(id)로 찾은 버튼을 삽입합
     *
     * @return List<Button>: 0~9까지의 버튼이 담긴 리스트를 반환함
     * @param v: findViewById를 사용하기 위한 View
     * */
    private fun getButtons(v: View): List<Button> {
        val list: MutableList<Button> = mutableListOf()

        for (i in 0..9) {
            val id = "btn_${i}_pin_number"
            list.add(v.findViewById(resources.getIdentifier(id, "id", requireActivity().packageName)))
        }

        return list
    }
}