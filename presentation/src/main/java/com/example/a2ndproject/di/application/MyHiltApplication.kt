package com.example.a2ndproject.di.application

import android.app.Application
import com.example.a2ndproject.ui.view.utils.Preference
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyHiltApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Preference.init(applicationContext)
    }
}