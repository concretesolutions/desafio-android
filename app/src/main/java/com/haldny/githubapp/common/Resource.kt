package com.haldny.githubapp.common

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

data class Resource<out T>(val status: Status, val data: T? = null, val throwable: Throwable? = null) {

    enum class Status { SUCCESS, ERROR, LOADING }

    companion object {

        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data)
        }

        fun <T> error(throwable: Throwable?): Resource<T> {
            return Resource(Status.ERROR, throwable = throwable)
        }

        fun <T> loading(): Resource<T> {
            return Resource(Status.LOADING)
        }

    }
}

fun <T> LiveData<Resource<T>>.observeResource(
    owner: LifecycleOwner,
    onSuccess: (T) -> Unit,
    onError: (Throwable) -> Unit,
    onLoading: () -> Unit) {

    observe(owner, Observer { resource ->
        when (resource.status) {
            Resource.Status.SUCCESS -> { resource.data?.let { onSuccess(it) } }
            Resource.Status.ERROR -> { resource.throwable?.let { onError(it) } }
            Resource.Status.LOADING -> { onLoading() }
        }

    })
}