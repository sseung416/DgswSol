package com.example.a2ndproject.ui.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.a2ndproject.R
import com.example.a2ndproject.ui.viewmodel.fragment.HoldViewModel

class HoldActivity : AppCompatActivity() {

    private val viewModel: HoldViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hold)

        viewModel.from.value = intent.getStringExtra("from")
    }
}