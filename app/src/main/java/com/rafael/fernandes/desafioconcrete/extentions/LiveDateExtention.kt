package com.rafael.fernandes.desafioconcrete.extentions

import androidx.lifecycle.MutableLiveData
import com.rafael.fernandes.desafioconcrete.presentation.resources.Resource
import com.rafael.fernandes.desafioconcrete.presentation.resources.ResourceState

fun <T> MutableLiveData<Resource<T>>.setSuccess(data: T?) =
    postValue(Resource(ResourceState.SUCCESS, data))

fun <T> MutableLiveData<Resource<T>>.setLoading() =
    postValue(Resource(ResourceState.LOADING, value?.data))

fun <T> MutableLiveData<Resource<T>>.setError(message: String? = null) =
    postValue(Resource(ResourceState.ERROR, value?.data, message))
