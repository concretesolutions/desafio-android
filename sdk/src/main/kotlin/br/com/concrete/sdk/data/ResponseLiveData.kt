package br.com.concrete.sdk.data

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import br.com.concrete.sdk.extension.observe
import br.com.concrete.sdk.extension.observeUntil
import br.com.concrete.sdk.model.DataResult
import br.com.concrete.sdk.model.type.ERROR
import br.com.concrete.sdk.model.type.LOADING

abstract class ResponseLiveData<T> : LiveData<DataResult<T>>() {

    val errorLiveData = MutableLiveData<Throwable>()

    fun observeData(owner: LifecycleOwner, success: (data: T?) -> Unit) = observe(owner) {
        if (it.status != ERROR) success(it.data)
        else errorLiveData.value = it.error
    }

    fun observeError(owner: LifecycleOwner, error: (error: Throwable) -> Unit) = errorLiveData.observe(owner, error)

    fun observeSingleData(owner: LifecycleOwner, success: (data: T?) -> Unit) = observeUntil(owner) {
        if (it.status != ERROR) success(it.data)
        else errorLiveData.value = it.error

        it.status != LOADING
    }

    fun observeSingleError(owner: LifecycleOwner, error: (error: Throwable) -> Unit) = errorLiveData.observeUntil(owner) {
        error(it)
        true
    }

    fun getData() = value?.data

    fun getError() = value?.error

    fun getStatus() = value?.status
}