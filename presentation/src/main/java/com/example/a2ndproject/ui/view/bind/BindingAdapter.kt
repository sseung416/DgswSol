package com.example.a2ndproject.ui.view.bind

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.EditText
import android.widget.ImageView
import androidx.core.widget.addTextChangedListener
import androidx.databinding.BindingAdapter
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.google.android.material.textfield.TextInputLayout

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("setVisible")
    fun View.setVisible(isVisible: Boolean) {
        visibility = when(isVisible) {
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
        Glide.with(context).load(url).into(this)
    }

}