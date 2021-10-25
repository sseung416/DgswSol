package com.example.a2ndproject.ui.view.bind

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.EditText
import android.widget.ImageView
import androidx.core.widget.addTextChangedListener
import androidx.databinding.BindingAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.textfield.TextInputLayout

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("setVisible")
    fun View.setVisible(isVisibility: Boolean) {
        visibility = when(isVisibility) {
            true -> VISIBLE
            false -> GONE
        }
    }

    @JvmStatic
    @BindingAdapter("setError")
    fun TextInputLayout.setError(error: String) {
        this.error = error
    }

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun ImageView.loadImage(url: String?) {

    }

}