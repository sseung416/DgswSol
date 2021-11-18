package com.example.a2ndproject.ui.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.a2ndproject.databinding.FragmentDefaultDialogBinding

class DefaultDialogFragment : DialogFragment() {

    private lateinit var binding: FragmentDefaultDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDefaultDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvErrorDialog.text = arguments?.getString("msg")

        binding.btnConfirmDialog.setOnClickListener {
            dismiss()
        }
    }

}