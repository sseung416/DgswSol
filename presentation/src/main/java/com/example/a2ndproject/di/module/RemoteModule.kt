package com.example.a2ndproject.di.module

import com.example.data.datasource.AccountDataSource
import com.example.data.datasource.TransferDataSource
import com.example.data.datasource.UserDataSource
import com.example.data.network.remote.AccountRemote
import com.example.data.network.remote.TransferRemote
import com.example.data.network.remote.UserRemote
import com.example.data.network.service.AccountService
import com.example.data.network.service.TransferService
import com.example.data.network.service.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Singleton
    @Provides
    fun providesUserRemote(service: UserService) =
        UserRemote(service)

    @Singleton
    @Provides
    fun providesAccountRemote(service: AccountService) =
        AccountRemote(service)

    @Singleton
    @Provides
    fun providesTransferRemote(service: TransferService) =
        TransferRemote(service)
}