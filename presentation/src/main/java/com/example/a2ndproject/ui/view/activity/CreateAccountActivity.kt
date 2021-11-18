package com.example.a2ndproject.ui.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.a2ndproject.R
import com.example.a2ndproject.databinding.ActivityCreateAccountBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateAccountActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateAccountBinding.inflate(layoutInflater)
        setSupportActionBar(binding.toolbarAddAccount)
        setContentView(binding.root)


        binding.toolbarAddAccount.setOnMenuItemClickListener {
            when (it?.itemId) {
                R.id.toolbar_btn_close_add_account ->
                    navigateToMain()
            }

            return@setOnMenuItemClickListener true
        }
    }

    private fun navigateToMain() {
        startActivity(Intent(this, MainActivity::class.java))
    }
}