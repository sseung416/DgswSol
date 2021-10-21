package com.example.a2ndproject.ui.view.utils

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.example.a2ndproject.R
import com.example.a2ndproject.ui.view.fragment.EventDialogFragment

object MessageUtil {
    fun showDialog(fragmentActivity: FragmentActivity, title: String, content: String) {
        val dialog = EventDialogFragment(R.layout.event_dialog_fragment)

        dialog.show(fragmentActivity.supportFragmentManager, "dialog")
    }

    fun showToast(context: Context, content: String) {
        Toast.makeText(context, content, Toast.LENGTH_SHORT).show()
    }

    fun printLog(tag: String, content: String) {
        Log.d(tag, content);
    }
}