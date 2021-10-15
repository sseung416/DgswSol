package com.example.a2ndproject.ui.view.utils

import com.example.a2ndproject.ui.view.utils.Preference.token
import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder().addHeader("authorization", token.token).build()
        return chain.proceed(request)
    }
}