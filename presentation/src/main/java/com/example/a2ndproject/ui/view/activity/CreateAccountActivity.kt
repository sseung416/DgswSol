package com.example.a2ndproject.ui.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.a2ndproject.R
import com.example.a2ndproject.databinding.ActivityCreateAccountBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateAccountActivity : AppCompatActivity(), Toolbar.OnMenuItemClickListener {

    private lateinit var binding: ActivityCreateAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateAccountBinding.inflate(layoutInflater)
        setSupportActionBar(binding.toolbarAddAccount)
        setContentView(R.layout.activity_create_account)
    
        binding.toolbarAddAccount.setOnMenuItemClickListener(this)

        binding.toolbarBtnCloseAddAccount.setOnClickListener {
            navigateToMain()
        }
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.toolbar_btn_close_add_account -> {
                startActivity(Intent(this, MainActivity::class.java))
            }
        }

        return true
    }

    private fun navigateToMain() {
        startActivity(Intent(this, MainActivity::class.java))
    }
}