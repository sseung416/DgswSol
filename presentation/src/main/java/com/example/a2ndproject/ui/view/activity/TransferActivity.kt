package com.example.a2ndproject.ui.view.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.a2ndproject.databinding.ActivityTransferBinding
import com.example.a2ndproject.databinding.TransferChooseFragmentBinding
import com.example.a2ndproject.ui.view.fragment.NumberPadMoneyFragment
import com.example.a2ndproject.ui.view.fragment.NumberPadMoneyFragmentDirections
import com.example.a2ndproject.ui.view.fragment.TransferChooseFragment
import com.example.a2ndproject.ui.viewmodel.fragment.TransferViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TransferActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTransferBinding
    private val viewModel: TransferViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTransferBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.fromAccount = intent.getStringExtra("from")!!

        binding.toolbarBtnCloseTransfer.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}