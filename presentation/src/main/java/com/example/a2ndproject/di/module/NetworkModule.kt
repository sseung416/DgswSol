package com.example.a2ndproject.di.module

import com.example.a2ndproject.ui.view.utils.TokenInterceptor
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val AWS_BASE_URL: String = "http://ec2-18-119-126-184.us-east-2.compute.amazonaws.com:5000/"
    private const val LOCAL_BASE_URL: String = "http://10.80.161.222:5000/"

    @Singleton
    @Provides
    fun providesOkhttpClient(): OkHttpClient =
        OkHttpClient.Builder().addInterceptor(TokenInterceptor()).build()

    @Singleton
    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val gson = GsonBuilder().setLenient().create()

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addConverterFactory(ScalarsConverterFactory.create())
            .baseUrl(LOCAL_BASE_URL)
            .client(okHttpClient)
            .build()
    }


}

