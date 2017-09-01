package br.com.concrete.sdk.data

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import br.com.concrete.sdk.extension.observe
import br.com.concrete.sdk.model.DataResult
import br.com.concrete.sdk.model.type.ERROR

abstract class ResponseLiveData<T> : LiveData<DataResult<T>>() {

    val errorLiveData = MutableLiveData<Throwable>()

    fun observeData(owner: LifecycleOwner, success: (data: T?) -> Unit) = observe(owner) {
        if (it.status != ERROR) success.invoke(it.data)
        else errorLiveData.value = it.error
    }

    fun observeError(owner: LifecycleOwner, error: (error: Throwable) -> Unit) = errorLiveData.observe(owner, error)

}