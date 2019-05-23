package com.uharris.desafio_android.data.base

sealed class Result<out T: Any> {
    data class Success<out T: Any>(val data: T): Result<T>()
    data class Error(val failure: Failure): Result<Nothing>()
}