package com.example.data.base

import android.util.Log
import com.example.data.entity.MsgResponse
import retrofit2.Response
import kotlin.jvm.Throws

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
            val err = response.errorBody()?.string()
            Log.d(TAG, "checkError: $err")
//            Throwable(err.toString())
        } else {
//            val res = response.body()
//            Log.d(TAG, "checkError: ${res.msg}")
//
//            if (res.msg == "exist" || res.msg == "fail") {
//                Throwable(res.toString())
//            }
        }
    }
}