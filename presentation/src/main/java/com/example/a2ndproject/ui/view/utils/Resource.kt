package com.example.a2ndproject.ui.view.utils

import android.app.Application
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.example.a2ndproject.R

fun ViewModel.getString(application: Application, id: Int) =
    application.resources.getString(id)

fun ViewModel.getColor(application: Application, id: Int) =
    ContextCompat.getColor(application.applicationContext, id)

fun Fragment.getString(id: Int) =
    resources.getString(id)

fun Fragment.getNotRegularErrorString(str: String) =
    str + resources.getString(R.string.error_not_regular)

fun Fragment.getEmptyErrorString(str: String) =
    str + "를 " + resources.getString(R.string.error_input)

fun Fragment.getColor(id: Int) =
    ContextCompat.getColor(requireContext(), id)