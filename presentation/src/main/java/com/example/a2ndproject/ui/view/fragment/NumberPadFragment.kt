/** 숫자 패드 프래그먼트 */

package com.example.a2ndproject.ui.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.fragment.app.activityViewModels
import com.example.a2ndproject.R
import com.example.a2ndproject.databinding.NumberPadFragmentBinding
import com.example.a2ndproject.ui.view.base.BaseFragment
import com.example.a2ndproject.ui.view.utils.MessageUtil
import com.example.a2ndproject.ui.viewmodel.fragment.NumberPadViewModel

class NumberPadFragment : BaseFragment<NumberPadFragmentBinding>() {

    private val viewModel: NumberPadViewModel by activityViewModels()

    private lateinit var keyButtonList: List<Button>

    override fun getLayoutResId(): Int =
        R.layout.number_pad_fragment

    override fun setViewModel() {}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        keyButtonList = getButtons(view)

        // list의 view의 클릭 리스너를 설정함
        keyButtonList.forEach {
            setOnClickNumberButtonListener(it)
        }

        binding.btnDeletePinNumber.setOnClickListener {
            if (viewModel.numberList.value!!.size != 0) {
                viewModel.removeNumber()
                Log.d(TAG, "click: delete")
            }
        }
    }

    /** numberButtons list에 string값(id)로 찾은 버튼을 삽입합 **/
    private fun getButtons(v: View): List<Button> {
        val list: MutableList<Button> = mutableListOf()

        for (i in 0..9) {
            val id = "btn_${i}_pin_number"
            list.add(v.findViewById(resources.getIdentifier(id, "id", requireActivity().packageName)))
        }

        return list
    }

    private fun setOnClickNumberButtonListener(numberBtn: Button) {
        /* resource id로 resource name 구하고, name에서 숫자만 추출해 pinNumber에 저장함. */

        numberBtn.setOnClickListener {
            if (viewModel.numberList.value!!.size <= 6) {
                val number =
                    it.resources.getResourceEntryName(it.id).replace(("[^0-9]").toRegex(), "").toInt()
                viewModel.addNumber(number)
                Log.d(TAG, "click: $number")
            }
        }
    }
}