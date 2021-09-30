package com.example.a2ndproject.ui.view.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<T : ViewDataBinding, V : BaseViewModel>
    : AppCompatActivity() {
    protected lateinit var binding: T

    protected abstract val viewModel: V

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

    }
}