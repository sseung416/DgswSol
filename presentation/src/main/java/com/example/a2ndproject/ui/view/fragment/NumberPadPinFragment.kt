/**
 * 로그인 등을 이용할 때 사용할 핀버튼 프래그먼트
 * OnClick() methode override 위해 View.OnClickListener implements 함.
 *
 * @author 최승연
 * @date 2021-09-08
 */

package com.example.a2ndproject.ui.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.a2ndproject.R
import com.example.a2ndproject.databinding.NumberPadPinFragmentBinding
import com.example.a2ndproject.ui.view.base.BaseFragment
import com.example.a2ndproject.ui.view.base.BaseViewModel
import com.example.a2ndproject.ui.view.base.BaseViewModelFactory
import com.example.a2ndproject.ui.viewmodel.fragment.LoginViewModel
import com.example.a2ndproject.ui.viewmodel.fragment.NumberPadViewModel
import com.example.a2ndproject.ui.viewmodel.fragment.SignUpViewModel
import com.example.domain.entity.request.SignUp
import com.example.domain.usecase.user.PostQuickLoginUseCase
import com.example.domain.usecase.user.PostQuickSignUpUseCase
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject

//@AndroidEntryPoint
class NumberPadPinFragment : BaseFragment<NumberPadPinFragmentBinding>() {

//    @Inject
//    lateinit var postQuickLoginUseCase: PostQuickLoginUseCase
//    @Inject
//    lateinit var postQuickSignupUseCase: PostQuickSignUpUseCase
//
//    private val loginViewModel: LoginViewModel by activityViewModels {
//        BaseViewModelFactory(postQuickLoginUseCase)
//    }
//    private val signUpViewModel: SignUpViewModel by activityViewModels {
//        BaseViewModelFactory(postQuickSignupUseCase)
//    }

    private val numberPadViewModel: NumberPadViewModel by activityViewModels()

    private lateinit var pinCardList: List<CardView>

    override fun getLayoutResId(): Int =
        R.layout.number_pad_pin_fragment

    override fun setViewModel() {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pinCardList = getCards(view)

        observe()
    }

    /**
     * getIdentifier()를 사용해 6개의 카드뷰의 view가 담긴 리스트를 가져옴
     *
     * @return List<CardView>: 카드뷰가 담긴 리스트
     * @param v: findViewById 사용을 위함
     * */
    private fun getCards(v: View): List<CardView> {
        val list: MutableList<CardView> = mutableListOf()

        for (i in 1..6) {
            val id = "cv_pin${i}_pin_number"
            list.add(v.findViewById(resources.getIdentifier(id, "id", requireActivity().packageName)))
        }

        return list
    }

    private fun observe() = with(numberPadViewModel) {
        var size = numberList.value?.size

        numberList.observe(viewLifecycleOwner, {
            when (it.size) {
                6 -> {
                    if (true) {
                        Log.d(TAG, it.toString())
                        //알맞는지 검사
                    } else {
                        Thread.sleep(100)
                        binding.tvErrorPinNumber.visibility = VISIBLE
                        clearPinShape()
                        this.numberList.value = Stack() // 이걸로 스택 비워지는지 확인
                    }
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

        })
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
    private fun clearPinShape() {
        pinCardList.forEach {
            it.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.grey))
        }
    }
}