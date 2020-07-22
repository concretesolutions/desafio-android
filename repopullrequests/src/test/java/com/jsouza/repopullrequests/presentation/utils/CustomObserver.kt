package com.jsouza.repopullrequests.presentation.utils

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.Observer

class CustomObserver<T>(private val handler: (T) -> Unit) : Observer<T>, LifecycleOwner {
    private val lifecycle = LifecycleRegistry(this)

    init {
        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
    }

    override fun getLifecycle(): Lifecycle = lifecycle

    override fun onChanged(t: T) {
        handler(t)
        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    }
}
