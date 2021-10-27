/**
 * 로그인 등을 이용할 때 사용할 핀버튼 프래그먼트
 * OnClick() methode override 위해 View.OnClickListener implements 함.
 *
 * @author 최승연
 * @date 2021-09-08
 */

package com.example.a2ndproject.ui.view.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
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
import com.example.a2ndproject.ui.view.utils.getStringText
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

        pinCardList = getCards(view)

        observeNumPad()
        observePin()
    }

    private fun observeNumPad() = with(numberPadViewModel) {
        var size = numberList.value?.size

        numberList.observe(viewLifecycleOwner) {
            when (it.size) {
                // 비밀번호 6자리가 모두 입력되었을 때, type에 따라 서버 통신 실행
                6 -> {

                }

                else -> {
                    var isDelete = false
                    if (size != null) {
                        if (size > it.size) {
                            isDelete = true
                        }
                    }

                    fillPinShape(isDelete, it.lastIndex)
                }
            }
        }
    }

    private fun observePin() = with(numberPadPinViewModel) {
        isFailure.observe(viewLifecycleOwner) {
            clearPin()
            MessageUtil.showDialog(requireActivity(), "알림", getStringText(R.string.fail_server))
        }

        isSuccessLogin.observe(viewLifecycleOwner) {
            when (it.logintoken) {
                null -> {
                    clearPin()
                    MessageUtil.showDialog(requireActivity(), "알림", "로그인에 실패하셨씀당!")
                }

                else -> {
                    token = it.logintoken!!
                    navigateToMain()
                }
            }
        }

        isSuccessSignUp.observe(viewLifecycleOwner) {
            when (it) {
                getStringText(R.string.server_res_success) ->
                    navigateToMain()

                getStringText(R.string.server_res_fail) -> {
                    clearPin()
                    MessageUtil.showDialog(requireActivity(), "알림", "어쨋든 실패했어여...")
                }
            }
        }
    }

    private fun asdf() {
        when (args.type) {
            FragmentType.PIN_QUICK_SIGN_UP.type ->
                numberPadPinViewModel.quickSignUp(numberPadViewModel.getNumber())

            FragmentType.PIN_QUICK_SIGN_IN.type ->
                numberPadPinViewModel.quickLogin(numberPadViewModel.getNumber())

            FragmentType.PIN_ACCOUNT_PW.type ->
                ""

            else ->
                Log.d("NumberPadPin", "navArgs 값 체크하기!")
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
}