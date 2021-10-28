package com.example.a2ndproject.ui.view.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

object Preference {
    private lateinit var sharedPreferences: SharedPreferences

    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences("authorization", MODE_PRIVATE)
    }

    var token: String
        get() = sharedPreferences.getString("token", "")?:""
        set(value) = sharedPreferences.edit().putString("token", value).apply()
}