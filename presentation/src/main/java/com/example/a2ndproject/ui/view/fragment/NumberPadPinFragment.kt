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
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NumberPadPinFragment : BaseFragment<NumberPadPinFragmentBinding>() {

    private val numberPadViewModel: NumberPadViewModel by activityViewModels()
    private val numberPadPinViewModel: NumberPadPinViewModel by activityViewModels()

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
        }

        observePin()
    }

    private fun init(view: View) {
        pinCardList = getCards(view)

        if (args.type == FragmentType.PIN_ACCOUNT_PW.type) {
            binding.cvPin5PinNumber.visibility = GONE
            binding.cvPin6PinNumber.visibility = GONE
        }
    }

    private fun observeCreateAccount() = with (numberPadPinViewModel) {
        numberPadViewModel.numberList.observe(viewLifecycleOwner) {
            if (it.size == 4) {
                createAccount(args.name, numberPadViewModel.getNumber())
            }
        }

        isSuccessCreate.observe(viewLifecycleOwner) {
            if (it.isNotBlank()) {
                navController.navigate(R.id.action_numberPadPinFragment_to_createAccountSuccessFragment)
            }
        }

        isFailure.observe(viewLifecycleOwner) {
            var msg: String = when (it) {
                "fail" -> "계좌 개설에 실패하였습니다.\n다시 시도해 주세요."
                else -> this@NumberPadPinFragment.getString(R.string.fail_server)
            }

            MessageUtil.showDialog(requireActivity(), msg)
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

    private fun observeNumPad() = with(numberPadViewModel) {
        numberList.observe(viewLifecycleOwner) {
            // todo 첫 type 검사 후에도 계속 검사함 -> 나중에 로직 바꾸기
            when (it.size) {
                4 -> {
                    when (args.type) {
                        FragmentType.PIN_ACCOUNT_PW.type -> {
                        }
                    }
                }

                // 비밀번호 6자리가 모두 입력되었을 때, type에 따라 서버 통신 실행
                6 -> {
                    when (args.type) {
                        FragmentType.PIN_QUICK_SIGN_UP.type ->
                            numberPadPinViewModel.quickSignUp(numberPadViewModel.getNumber())

                        FragmentType.PIN_QUICK_SIGN_IN.type ->
                            numberPadPinViewModel.quickLogin(numberPadViewModel.getNumber())

                        FragmentType.PIN_ACCOUNT_PW.type -> ""
//                            numberPadPinViewModel.createAccount()

                        else ->
                            Log.d("NumberPadPin", "navArgs 값 체크하기!")
                    }
                }

                else -> {
//                    var isDelete = false
//                    if (size != null) {
//                        if (size > it.size) {
//                            isDelete = true
//                        }
//                    }
//
//                    Log.d("asdf", it.lastIndex.toString())
//                    fillPinShape(isDelete, it.lastIndex)
                }
            }
        }
    }

    private fun observePin() = with(numberPadPinViewModel) {
        // todo 오류 처리 글로..

        isFailure.observe(viewLifecycleOwner) {
            if (it.isNullOrBlank()) {
                clearPin()
                MessageUtil.showDialog(requireActivity(), this@NumberPadPinFragment.getString(R.string.fail_server))
            }
        }

        isSuccessLogin.observe(viewLifecycleOwner) {
            when (it.msg) {
                "fail" -> {
                    clearPin()
                    MessageUtil.showDialog(requireActivity(), "로그인에 실패하였습니다.")
                }

                else -> {
                    token = it.msg!!
                    navigateToMain()
                }
            }

        }

        isSuccessSignUp.observe(viewLifecycleOwner) {
            when (it) {
                this@NumberPadPinFragment.getString(R.string.server_res_success) ->
                    navigateToMain()

                this@NumberPadPinFragment.getString(R.string.server_res_fail) -> {
                    clearPin()
                    MessageUtil.showDialog(requireActivity(), "어쨋든 실패했어여...")
                }
            }
        }
    }

    private fun navigateToMain() {
        startActivity(Intent(requireActivity(), MainActivity::class.java))
    }

    /**
     * getIdentifier()를 사용해 6개의 카드뷰의 view가 담긴 리스트를 가져옴
     * */
    private fun getCards(v: View): List<CardView> {
        val list: MutableList<CardView> = mutableListOf()

        for (i in 1..6) {
            val id = "cv_pin${i}_pin_number"
            list.add(v.findViewById(resources.getIdentifier(id, "id", requireActivity().packageName)))
        }

        return list
    }

    /**
     * 키패드 클릭 시 핀 모양의 색을 바꿈.
     *
     * @param isDelete: 삭제 버튼을 눌렀는지, 숫자 버튼을 눌렀는지 판별을 위함.
     * */
    private fun fillPinShape(isDelete: Boolean, pos: Int) {
        val color: Int = when (isDelete) {
            true -> R.color.grey
            false -> R.color.black
        }

        pinCardList[pos].setCardBackgroundColor(ContextCompat.getColor(requireContext(), color))
    }

    /**
     * 저장된 핀번호(pinNumber item)를 모두 삭제하고 핀 모양의 색을 grey로 바꿈
     * */
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