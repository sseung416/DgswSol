package com.example.data.base

import android.util.Log
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

    @Throws(java.lang.RuntimeException::class)
    private fun <T> checkError(response: Response<T>) {
        // todo
        if (!response.isSuccessful) {
            val err = response.errorBody()?.string()
            Log.d(TAG, "checkError: $err")
            Throwable(err.toString())
//            throw RuntimeException(err.toString())
        } else if (response.isSuccessful) {
            Log.d(TAG, response.body().toString())
            val res = response.body()

            if (res == "exist" || res == "fail") {
                Log.d(TAG, "checkError: $res")
                Throwable(res.toString())
            }
//            throw RuntimeException(res.toString())
        }
    }
}