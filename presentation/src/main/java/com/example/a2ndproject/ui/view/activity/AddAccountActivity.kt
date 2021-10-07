package com.example.a2ndproject.ui.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import com.example.a2ndproject.R
import com.example.a2ndproject.databinding.ActivityAddAccountBinding

class AddAccountActivity : AppCompatActivity(), Toolbar.OnMenuItemClickListener {

    private lateinit var binding: ActivityAddAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddAccountBinding.inflate(layoutInflater)
        setSupportActionBar(binding.toolbarAddAccount)
        setContentView(R.layout.activity_add_account)

        binding.toolbarAddAccount.setOnMenuItemClickListener(this)
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.toolbar_btn_close_add_account -> {
                startActivity(Intent(this, MainActivity::class.java))
            }
        }

        return true
    }
}