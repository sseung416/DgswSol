package com.example.a2ndproject.ui.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.a2ndproject.R

class IntroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_2ndProject)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)
    }

}