package com.example.githubtest.data.model

data class ViewStateModel<T>(
    val status: Status,
    val model: T? = null,
    val errors: Throwable? = null
) {
    enum class Status {
        LOADING, SUCCESS, ERROR
    }
}