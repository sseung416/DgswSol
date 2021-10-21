package com.example.a2ndproject.ui.view.bind

import android.text.Editable
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.EditText
import android.widget.ImageView
import androidx.core.widget.addTextChangedListener
import androidx.databinding.BindingAdapter
import androidx.databinding.adapters.TextViewBindingAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.internal.TextWatcherAdapter
import com.google.android.material.textfield.TextInputLayout

object BindingAdapter {
    @BindingAdapter("setVisible")
    fun View.setVisible(isVisibility: Boolean) {
        visibility = when(isVisibility) {
            true -> VISIBLE
            false -> GONE
        }
    }

    @BindingAdapter("setError")
    fun TextInputLayout.setError(error: String) {
        this.error = error
    }

    @BindingAdapter("onTextChangedListener")
    fun EditText.setOnTextChangedListener(listener: Unit) {
        this.addTextChangedListener {
            listener
        }
    }

    @BindingAdapter("imageUrl")
    fun ImageView.loadImage(url: String?) {

    }

    @BindingAdapter("setCurrentItem")
    fun ViewPager2.setCurrentItem(pos: Int) {
        this.currentItem = pos
    }
}