@file:JvmName("LiveDataUtils")

package br.com.concrete.sdk.extension

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.Observer
import br.com.concrete.sdk.data.remote.factory.ResponseLiveData
import br.com.concrete.sdk.model.DataResult

fun <T> LiveData<T>.observe(owner: LifecycleOwner, observer: ((T) -> Unit)) = observe(owner, Observer { it?.let(observer) })

internal fun <T> MediatorLiveData<DataResult<T>>.receiveData(source: ResponseLiveData<T>, onChanged: ((DataResult<retrofit2.Response<T>>) -> Unit)) = addSource(source) {
    removeSource(source)
    it?.let(onChanged)
}
