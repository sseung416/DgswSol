package com.example.domain.base

abstract class BaseUseCase {
    abstract suspend fun invoke()
}