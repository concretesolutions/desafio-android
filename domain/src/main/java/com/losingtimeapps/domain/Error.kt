package com.losingtimeapps.domain

import retrofit2.HttpException
import java.net.UnknownHostException

class ParseError {

    fun parse(throwable: Throwable): Error {

        return when (throwable) {
            is HttpException -> when (throwable.code()) {
                404 -> Error.NotFoundError
                422 -> Error.RepositoryInvalidLanguageError
                500 -> Error.InternalServerError
                else -> Error.UnexpectedError
            }
            is UnknownHostException -> Error.NetworkConnectionError
            else -> Error.UnexpectedError
        }
    }
}

enum class Error {

    EmptyLanguageError,
    EmptyRepositoryNameError,
    NotFoundError,
    RepositoryInvalidLanguageError,
    NetworkConnectionError,
    UnexpectedError,
    InternalServerError

}