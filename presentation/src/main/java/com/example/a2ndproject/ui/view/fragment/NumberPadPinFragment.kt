/** 핀버튼 프래그먼트 (숫자 암호 입력) */

package com.example.a2ndproject.ui.view.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.GONE
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.a2ndproject.R
import com.example.a2ndproject.databinding.NumberPadPinFragmentBinding
import com.example.a2ndproject.ui.view.activity.MainActivity
import com.example.a2ndproject.ui.view.base.BaseFragment
import com.example.a2ndproject.ui.view.data.FragmentType
import com.example.a2ndproject.ui.view.utils.MessageUtil
import com.example.a2ndproject.ui.view.utils.Preference.token
import com.example.a2ndproject.ui.viewmodel.fragment.NumberPadPinViewModel
import com.example.a2ndproject.ui.viewmodel.fragment.NumberPadViewModel
import com.example.a2ndproject.ui.viewmodel.fragment.TransferViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NumberPadPinFragment : BaseFragment<NumberPadPinFragmentBinding>() {

    private val numberPadViewModel: NumberPadViewModel by activityViewModels()
    private val numberPadPinViewModel: NumberPadPinViewModel by activityViewModels()
    private val transferViewModel: TransferViewModel by activityViewModels()

    private lateinit var pinCardList: List<CardView>

    private val args by navArgs<NumberPadPinFragmentArgs>()

    override fun getLayoutResId(): Int =
        R.layout.number_pad_pin_fragment

    override fun setViewModel() {}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init(view)

        when (args.type) {
            FragmentType.PIN_QUICK_SIGN_IN.type -> observeLogin()

            FragmentType.PIN_QUICK_SIGN_UP.type -> observeSignUp()

            FragmentType.PIN_CREATE_ACCOUNT_PW.type -> observeCreateAccount()

            FragmentType.PIN_ACCOUNT_PW.type -> observeTransfer()
        }

        observePin()
        observeFailure()
    }

    private fun init(view: View) {
        pinCardList = getCards(view)

        if (args.type == FragmentType.PIN_ACCOUNT_PW.type ||
                args.type == FragmentType.PIN_CREATE_ACCOUNT_PW.type) {
            binding.cvPin5PinNumber.visibility = GONE
            binding.cvPin6PinNumber.visibility = GONE
        }
    }

    private fun observeCreateAccount() {
        numberPadViewModel.numberList.observe(viewLifecycleOwner) {
            if (it.size == 4) {
                numberPadPinViewModel.createAccount("안녕", numberPadViewModel.getNumber())
            }
        }

        numberPadPinViewModel.isSuccessCreate.observe(viewLifecycleOwner) {
            if (it.isNotBlank()) {
                navController.navigate(R.id.action_numberPadPinFragment_to_createAccountSuccessFragment)
            }
        }
    }

    private fun observeTransfer() = with (transferViewModel) {
        numberPadViewModel.numberList.observe(viewLifecycleOwner) {
            if (it.size == 4) {
                transferViewModel.putTransfer()
            }
        }

        isSuccess.observe(viewLifecycleOwner) {
            navController.navigate(R.id.action_numberPadPinFragment_to_transferSuccessFragment)
        }

        isFailure.observe(viewLifecycleOwner) {
            MessageUtil.showDialog(requireActivity(), "송금에 실패하였습니다. 다시 시도해주세요.")
        }
    }

    private fun observeSignUp() {
        numberPadViewModel.numberList.observe(viewLifecycleOwner) {
            if (it.size == 6) {
                numberPadPinViewModel.quickSignUp(numberPadViewModel.getNumber())
            }
        }
    }

    private fun observeLogin() {
        numberPadViewModel.numberList.observe(viewLifecycleOwner) {
            if (it.size == 6) {
                numberPadPinViewModel.quickLogin(numberPadViewModel.getNumber())
            }
        }
    }

    private fun observeFailure() {
        numberPadPinViewModel.isFailure.observe(viewLifecycleOwner) {
            val msg: String = when (it) {
                "fail" -> "다시 시도해 주세요."
                else -> this@NumberPadPinFragment.getString(R.string.fail_server)
            }

            MessageUtil.showDialog(requireActivity(), msg)
        }
    }

    private fun observePin() = with(numberPadViewModel) {
        // 변경되기 전 numberList 의 size
        // 현재 값과 비교해 숫자를 추가하였는지 삭제하였는지 검사
        var beforeSize = 0

        numberList.observe(viewLifecycleOwner) {
            when {
                // 처음 초기화될 때 오류 막기 위함
                it.lastIndex == -1 -> {}

                it.size < beforeSize -> {
                    val removePos = when {
                        it.size == 0 -> 0
                        else -> it.lastIndex + 1
                    }

                    removePinShape(removePos)
                }

                else -> fillPinShape(it.lastIndex)
            }

            beforeSize = it.size
        }
    }

    private fun navigateToMain() {
        startActivity(Intent(requireActivity(), MainActivity::class.java))
    }

    /** getIdentifier()를 사용해 6개의 카드뷰의 view가 담긴 리스트를 가져옴 **/
    private fun getCards(v: View): List<CardView> {
        val list: MutableList<CardView> = mutableListOf()

        for (i in 1..6) {
            val id = "cv_pin${i}_pin_number"
            list.add(v.findViewById(resources.getIdentifier(id, "id", requireActivity().packageName)))
        }

        return list
    }

    private fun fillPinShape(pos: Int) {
        pinCardList[pos].setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.blue))
    }

    private fun removePinShape(pos: Int) {
        pinCardList[pos].setCardBackgroundColor(
            ContextCompat.getColor(requireContext(), R.color.grey)
        )
    }

    /** 저장된 핀번호(pinNumber item)를 모두 삭제하고 핀 모양의 색을 grey로 바꿈 **/
    private fun clearPin() {
        pinCardList.forEach {
            it.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.grey))
        }

        numberPadViewModel.popAll()
    }

    override fun onStop() {
        super.onStop()
        requireActivity().viewModelStore.clear()
    }
}