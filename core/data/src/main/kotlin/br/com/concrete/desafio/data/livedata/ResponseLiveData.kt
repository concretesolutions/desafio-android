package br.com.concrete.desafio.data.livedata

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.MutableLiveData
import br.com.concrete.desafio.data.extension.observe
import br.com.concrete.desafio.data.extension.observeUntil
import br.com.concrete.desafio.data.model.DataResult
import br.com.concrete.desafio.data.model.enum.DataResultStatus.ERROR
import br.com.concrete.desafio.data.model.enum.DataResultStatus.LOADING

abstract class ResponseLiveData<T> : ComputableLiveData<DataResult<T>>() {

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