package com.example.a2ndproject.ui.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.a2ndproject.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ConnectAccountActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_connect_account)
    }
}