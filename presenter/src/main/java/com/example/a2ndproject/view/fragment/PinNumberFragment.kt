package com.example.a2ndproject.view.fragment

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
import com.example.a2ndproject.view.viewmodel.PinNumberViewModel
import java.util.*

class PinNumberFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: PinNumberFragmentBinding
    private lateinit var viewModel: PinNumberViewModel

    private var numberButtons: MutableList<Button> = mutableListOf()

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

        setButton(view)

        numberButtons.forEach {
            it.setOnClickListener(this)
        }
        binding.btnDeletePinNumber.setOnClickListener(this)
    }

    private fun init() {
        viewModel = ViewModelProvider(requireActivity()).get(PinNumberViewModel::class.java)
    }

    private fun setButton(v: View) {
        for (i in 0..9) {
            val id = "btn_${i}_pin_number"

            numberButtons.add(v.findViewById(resources.getIdentifier(id, "id", requireActivity().packageName)))
        }

    }

    private var pinNumber: Stack<Int> = Stack()

    override fun onClick(v: View?) {
        when (v!!.id) {
            binding.btnDeletePinNumber.id -> {
                if (pinNumber.size != 0)  {
                    fillPin(true)

                    Log.d("pinNumber", "pop: ${pinNumber.pop()}")
                }
            }

            // deleteButton이 아닌 숫자 패드 클릭시 실행
            // resource id로 resource name 구하고, name에서 숫자만 추출해 pinNumber에 저장
            else -> {
                val number = v.resources.getResourceEntryName(v.id).replace(("[^0-9]").toRegex(), "").toInt()

                Log.d("pinNumber", "click: $number")
                pinNumber.push(number)

                fillPin(false)
            }
        }
    }
    
    private fun fillPin(isDelete: Boolean) {
        val id = "cv_pin${pinNumber.size}_pin_number"
        val cardView: CardView = requireView().findViewById(resources.getIdentifier(id, "id", requireActivity().packageName))
        val color: Int = when (isDelete) {
            true -> R.color.grey
            false -> R.color.black
        }

        cardView.setCardBackgroundColor(ContextCompat.getColor(requireContext(), color))
    }

}