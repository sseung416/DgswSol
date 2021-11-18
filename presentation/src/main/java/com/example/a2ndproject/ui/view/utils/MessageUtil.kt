package com.example.a2ndproject.ui.view.utils

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.example.a2ndproject.ui.view.fragment.DefaultDialogFragment

object MessageUtil {

    fun showDialog(fragmentActivity: FragmentActivity, msg: String) {
        val dialog = DefaultDialogFragment()
        val bundle = Bundle()
        bundle.putString("msg", msg)

        dialog.arguments = bundle

        dialog.show(fragmentActivity.supportFragmentManager, "dialog")
    }

    fun showToast(context: Context, content: String) {
        Toast.makeText(context, content, Toast.LENGTH_SHORT).show()
    }

}