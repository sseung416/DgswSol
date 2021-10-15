package com.example.a2ndproject.ui.view.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.example.domain.entity.response.Token

object Preference {
    private lateinit var sharedPreferences: SharedPreferences

    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences("token", MODE_PRIVATE)
    }

    var token: Token
        get() {
            TODO()
//            sharedPreferences.getString()
        }
        set(value) = sharedPreferences.edit().putString("token", value.token).apply()
}