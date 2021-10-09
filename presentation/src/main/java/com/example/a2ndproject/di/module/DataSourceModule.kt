package com.example.a2ndproject.di.module

import android.service.autofill.UserData
import com.example.data.datasource.AccountDataSource
import com.example.data.datasource.TransferDataSource
import com.example.data.datasource.UserDataSource
import com.example.data.network.remote.AccountRemote
import com.example.data.network.remote.TransferRemote
import com.example.data.network.remote.UserRemote
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Singleton
    @Provides
    fun providesUserDataSource(remote: UserRemote) =
        UserDataSource(remote)

    @Singleton
    @Provides
    fun providesAccountDataSource(remote: AccountRemote) =
        AccountDataSource(remote)

    @Singleton
    @Provides
    fun providesTransferDataSource(remote: TransferRemote) =
        TransferDataSource(remote)
}