package com.example.a2ndproject.ui.view.utils

import android.app.Application
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel

fun ViewModel.getString(application: Application, id: Int) =
    application.resources.getString(id)

fun ViewModel.getColor(application: Application, id: Int) =
    ContextCompat.getColor(application.applicationContext, id)

fun Fragment.getString(id: Int) =
    resources.getString(id)

fun Fragment.getColor(id: Int) =
    ContextCompat.getColor(requireContext(), id)