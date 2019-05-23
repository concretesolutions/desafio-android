package com.uharris.desafio_android.data.base

sealed class Failure {
    object NetworkConnection: Failure()
    object ServerError: Failure()
}