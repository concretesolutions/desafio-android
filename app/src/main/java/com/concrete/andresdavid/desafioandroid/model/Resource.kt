package com.concrete.andresdavid.desafioandroid.model

data class Resource<out T>(val status: String, val data: T?, val message: String?) {
    companion object {
        const val SUCCESS = "SUCCESS"
        const val ERROR = "ERROR"
        const val LOADING = "LOADING"

        fun <T> success(data: T?): Resource<T> {
            return Resource(SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T?): Resource<T> {
            return Resource(ERROR, data, msg)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(LOADING, data, null)
        }
    }
}