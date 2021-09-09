package com.example.domain.base

abstract class ParamsUseCase<P> {
    abstract suspend fun invoke(repository: P)
}