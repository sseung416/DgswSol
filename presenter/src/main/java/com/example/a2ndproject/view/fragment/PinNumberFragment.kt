/**
 * 로그인 등을 이용할 때 사용할 핀버튼 프래그먼트
 * OnClick() methode override 위해 View.OnClickListener implements 함.
 *
 * @author 최승연, tmddusqls@naver.com
 * @date 2021-09-01
 */

package com.example.a2ndproject.view.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.a2ndproject.R
import com.example.a2ndproject.databinding.PinNumberFragmentBinding
import com.example.a2ndproject.view.activity.MainActivity
import com.example.a2ndproject.view.viewmodel.PinNumberViewModel
import java.util.*

class PinNumberFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: PinNumberFragmentBinding
    private lateinit var viewModel: PinNumberViewModel

    private lateinit var keyButtonList: List<Button>
    private lateinit var pinCardList: List<CardView>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.pin_number_fragment, container, false)

        init()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        keyButtonList = getButtons(view)
        pinCardList = getCards(view)

        keyButtonList.forEach {
            it.setOnClickListener(this)
        }
        binding.btnDeletePinNumber.setOnClickListener(this)
    }

    private fun init() {
        viewModel = ViewModelProvider(requireActivity()).get(PinNumberViewModel::class.java)
    }

    /**
     * numberButtons list에 string값(id)로 찾은 버튼을 삽입합.
     *
     * @date 2021-08-31
     * @return void
     * @param v: findViewById를 사용하기 위한 View.
     * */
    private fun getButtons(v: View): List<Button> {
        val list: MutableList<Button> = mutableListOf()

        for (i in 0..9) {
            val id = "btn_${i}_pin_number"
            list.add(v.findViewById(resources.getIdentifier(id, "id", requireActivity().packageName)))
        }

        return list
    }

    private fun getCards(v: View): List<CardView> {
        val list: MutableList<CardView> = mutableListOf()

        for (i in 1..6) {
            val id = "cv_pin${i}_pin_number"
            list.add(v.findViewById(resources.getIdentifier(id, "id", requireActivity().packageName)))
        }

        return list
    }

    private var pinNumber: Stack<Int> = Stack()

    override fun onClick(v: View?) {
        when (v!!.id) {
            binding.btnDeletePinNumber.id -> {
                if (pinNumber.size != 0)  {
                    fillPinShape(true)

                    Log.d("pinNumber", "pop: ${pinNumber.pop()}")
                }
            }

            /*
            * 삭제 버튼이 아닌 숫자 패드 클릭시 실행.
            * resource id로 resource name 구하고, name에서 숫자만 추출해 pinNumber에 저장함.
            */
            else -> {
                val number = v.resources.getResourceEntryName(v.id).replace(("[^0-9]").toRegex(), "").toInt()

                Log.d("pinNumber", "click: $number")
                pinNumber.push(number)

                fillPinShape(false)

                /*
                * 핀 번호를 6자리 입력했을 때 핀번호가 맞는지 검사함.
                * true 시 메인 화면으로 이동, false 시 pinNumber를 비우고 에러를 출력함.
                */
                if (pinNumber.size == 6) {
                    if (viewModel.isCorrectPin())
                        intentToMain()
                    else {
                        Thread.sleep(100)
                        clearPinShape()
                        binding.tvErrorPinNumber.text = viewModel.getErrorText()
                    }
                }
            }
        }
    }

    /**
     * 키패드 클릭 시 핀 모양의 색을 바꿈.
     *
     * @date 2021-08-31
     * @param isDelete: 삭제 버튼을 눌렀는지, 숫자 버튼을 눌렀는지 판별을 위함.
     * */
    private fun fillPinShape(isDelete: Boolean) {
        val color: Int = when (isDelete) {
            true -> R.color.grey
            false -> R.color.black
        }

        pinCardList[pinNumber.size-1].setCardBackgroundColor(ContextCompat.getColor(requireContext(), color))
    }

    /**
     * 저장된 핀번호(pinNumber item)를 모두 삭제하고 핀 모양의 색을 grey로 바꿈
     *
     * @date 2021-09-01
     * */
    private fun clearPinShape() {
        pinNumber.clear()

        pinCardList.forEach {
            it.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.grey))
        }
    }

    private fun intentToMain() {
        val intent = Intent(requireActivity(), MainActivity::class.java)
        startActivity(intent)
    }
}