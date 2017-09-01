@file:JvmName("LiveDataUtils")

package br.com.concrete.sdk.extension

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.Observer

fun <T> LiveData<T>.observe(owner: LifecycleOwner, observer: ((T) -> Unit)) = observe(owner, Observer { it?.let(observer) })

fun MediatorLiveData<Throwable>.addErrorSource(source: LiveData<Throwable>) = addSource(source) {
    value = it
}