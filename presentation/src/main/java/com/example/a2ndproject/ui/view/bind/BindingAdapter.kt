package com.example.a2ndproject.ui.view.bind

import android.view.View
import android.view.View.VISIBLE
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout

object BindingAdapter {
    @BindingAdapter("setVisible")
    fun View.setVisible(isVisibility: Boolean) {
        if (isVisibility)
            visibility = VISIBLE
    }

    @BindingAdapter("setError")
    fun TextInputLayout.setError(error: String) {
        this.error = error
    }

    @BindingAdapter("imageUrl")
    fun ImageView.loadImage(url: String?) {

    }


}