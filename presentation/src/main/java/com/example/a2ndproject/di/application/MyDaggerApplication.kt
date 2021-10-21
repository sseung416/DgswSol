package com.example.a2ndproject.di.application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyDaggerApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}