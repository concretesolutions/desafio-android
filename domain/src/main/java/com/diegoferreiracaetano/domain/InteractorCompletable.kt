package com.diegoferreiracaetano.domain

import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

abstract class InteractorCompletable<R : InteractorCompletable.Request> {

    protected abstract fun create(request: R): Completable

    fun execute(request: R): Completable {
        return create(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

    }

    abstract class Request
}
