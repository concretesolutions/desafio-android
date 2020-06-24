package com.germanofilho.core.helper

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

data class Resource<out T>(val status: Status, val data: T?, val boolean: Boolean?, val exception: Throwable?) {

    enum class Status { SUCCESS, ERROR, LOADING }

    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null, null)
        }

        fun <T> error(exception: Throwable?): Resource<T> {
            return Resource(Status.ERROR, null, null, exception)
        }

        fun <T> loading(boolean: Boolean?): Resource<T> {
            return Resource(Status.LOADING, null, boolean, null)
        }
    }
}

fun <T> MutableLiveData<Resource<T>>.observeResource(
    owner: LifecycleOwner,
    onSuccess: (T) -> Unit,
    onError: (Throwable) -> Unit,
    onLoading: (Boolean) -> Unit) {

    observe(owner, Observer { resource ->
        when (resource.status) {
            Resource.Status.SUCCESS -> {
                resource.data?.let {
                    onSuccess.invoke(it)
                }
            }
            Resource.Status.ERROR -> {
                resource.exception?.let {
                    onError.invoke(it)
                }
            }
            Resource.Status.LOADING -> {
                resource.boolean?.let {
                    onLoading.invoke(it)
                }
            }
        }
    })
}