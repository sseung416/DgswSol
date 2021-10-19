package com.example.a2ndproject.ui.view.app

import android.app.Application
import com.example.a2ndproject.ui.view.utils.Preference

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Preference.init(applicationContext)
    }
}