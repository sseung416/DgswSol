package com.example.data.base

import android.util.Log
import com.google.gson.Gson
import retrofit2.Response

abstract class BaseRemote<SV> {
    private val TAG: String = "BaseRemote"
    protected abstract val service: SV

    fun <T> getResponse(response: Response<T>): T {
        Log.d(TAG, response.raw().toString())
        checkError(response)
        return response.body()!!
    }

    private fun <T> checkError(response: Response<T>) {
        if (!response.isSuccessful) {
            val error = response.errorBody()?.string()
            Log.d(TAG, "checkError: $error")
            Throwable(error)
        }
    }
}