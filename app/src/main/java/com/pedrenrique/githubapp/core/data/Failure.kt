package com.pedrenrique.githubapp.core.data

sealed class Failure(
    val error: Throwable?
) {
    class Full(error: Throwable) : Failure(error)
    class Item(error: Throwable) : Failure(error)
    object Empty : Failure(null)
}