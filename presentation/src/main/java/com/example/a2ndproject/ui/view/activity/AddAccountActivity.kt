package com.example.a2ndproject.ui.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import com.example.a2ndproject.R
import com.example.a2ndproject.databinding.ActivityAddAccountBinding
import com.example.a2ndproject.ui.view.data.FragmentType
import com.example.a2ndproject.ui.view.fragment.AddAccountFragmentDirections

class AddAccountActivity : AppCompatActivity(), Toolbar.OnMenuItemClickListener {

//    private val navController by lazy {
//        findNavController()
//    }

    private lateinit var binding: ActivityAddAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddAccountBinding.inflate(layoutInflater)
        setSupportActionBar(binding.toolbarAddAccount)
        setContentView(R.layout.activity_add_account)

        init()

        binding.toolbarAddAccount.setOnMenuItemClickListener(this)

        binding.toolbarBtnCloseAddAccount.setOnClickListener {
            navigateToMain()
        }
    }

    private fun init() {
        // todo 나중에 처리
        val authType: Int = when(intent.getIntExtra("type", -1)) {
            FragmentType.ADD_ACCOUNT_CONNECT.type ->
                FragmentType.IDENTITY_AUTH_CONNECT.type

            FragmentType.ADD_ACCOUNT_CREATE.type ->
                FragmentType.IDENTITY_AUTH_CREATE.type

            else -> return
        }

//        navController.navigate(AddAccountFragmentDirections.actionAddAccountFragmentToIdentityAuthFragment(authType))
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