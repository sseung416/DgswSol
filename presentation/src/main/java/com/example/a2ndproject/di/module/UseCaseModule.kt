package com.example.a2ndproject.di.module

import com.example.domain.repository.AccountRepository
import com.example.domain.repository.TransferRepository
import com.example.domain.repository.UserRepository
import com.example.domain.usecase.account.PostCheckAccountUseCase
import com.example.domain.usecase.account.PostCreateAccountUseCase
import com.example.domain.usecase.transfer.GetTransferCheckUseCase
import com.example.domain.usecase.transfer.PostTransferPwUseCase
import com.example.domain.usecase.transfer.PostTransferUseCase
import com.example.domain.usecase.user.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun postCheckAccountUseCase(repository: AccountRepository) =
        PostCheckAccountUseCase(repository)

    @Singleton
    @Provides
    fun postCreateAccountUseCase(repository: AccountRepository) =
        PostCreateAccountUseCase(repository)

    @Singleton
    @Provides
    fun getTransferCheckUseCase(repository: TransferRepository) =
        GetTransferCheckUseCase(repository)

    @Singleton
    @Provides
    fun postTransferPwUseCase(repository: TransferRepository) =
        PostTransferPwUseCase(repository)

    @Singleton
    @Provides
    fun postTransferUseCase(repository: TransferRepository) =
        PostTransferUseCase(repository)

    @Singleton
    @Provides
    fun getDuplicateIdCheckUseCase(repository: UserRepository) =
        GetDuplicateIdCheckUseCase(repository)

    @Singleton
    @Provides
    fun getDuplicateNicknameCheckUseCase(repository: UserRepository) =
        GetDuplicateNicknameCheckUseCase(repository)

    @Singleton
    @Provides
    fun postLoginUseCase(repository: UserRepository) =
        PostLoginUseCase(repository)

    @Singleton
    @Provides
    fun postQuickLoginUseCase(repository: UserRepository) =
        PostQuickLoginUseCase(repository)

    @Singleton
    @Provides
    fun postQuickSignUpUseCase(repository: UserRepository) =
        PostQuickSignUpUseCase(repository)

    @Singleton
    @Provides
    fun postSignUpUseCase(repository: UserRepository) =
        PostSignUpUseCase(repository)

}