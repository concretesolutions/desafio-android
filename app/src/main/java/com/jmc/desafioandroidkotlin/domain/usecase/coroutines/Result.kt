package com.jmc.desafioandroidkotlin.domain.usecase.coroutines

sealed class Result<T> {
    data class OnSuccess<T>(val value: T) : Result<T>()
    data class OnError<T>(val throwable: Throwable) : Result<T>()
    class OnLoading<T> : Result<T>()
    class OnCancel<T> : Result<T>()
    class OnEmpty<T> : Result<T>()
}