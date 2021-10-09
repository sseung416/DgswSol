package com.example.a2ndproject.di.module

import com.example.data.datasource.AccountDataSource
import com.example.data.datasource.TransferDataSource
import com.example.data.datasource.UserDataSource
import com.example.data.repository.AccountRepositoryImpl
import com.example.data.repository.TransferRepositoryImpl
import com.example.data.repository.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun providesUserRepository(dataSource: UserDataSource) =
        UserRepositoryImpl(dataSource)

    @Singleton
    @Provides
    fun providesAccountRepository(dataSource: AccountDataSource) =
        AccountRepositoryImpl(dataSource)

    @Singleton
    @Provides
    fun providesTransferRepository(dataSource: TransferDataSource) =
        TransferRepositoryImpl(dataSource)
}