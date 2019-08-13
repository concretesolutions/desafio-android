package com.rafael.fernandes.desafioconcrete.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rafael.fernandes.desafioconcrete.extentions.setSuccess
import com.rafael.fernandes.desafioconcrete.presentation.resources.Resource
import retrofit2.Response


object ApiUtil {
    fun <T : Any> successCall(data: T) = createCall(Response.success(data))

    fun <T : Any> createCall(response: Response<T>) = MutableLiveData<Resource<T>>().apply {
        setSuccess(response.body())
    } as LiveData<Resource<T>>
}