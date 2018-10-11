package com.diegoferreiracaetano.domain

import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

abstract class InteractorFlowable<T, R : InteractorFlowable.Request> {

    protected abstract fun create(request: R): Flowable<T>

    fun execute(request: R): Flowable<T> {
        return create(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

    }

    abstract class Request
}
