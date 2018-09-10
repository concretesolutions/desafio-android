package com.concrete.desafioandroid.features.base

import io.reactivex.disposables.CompositeDisposable


abstract class BasePresenter<V: BaseView> {

    protected val compositeDisposable = CompositeDisposable()

    var view: V? = null

    fun attachView(view: V?) {
        this.view = view

        if(view == null) {
            throw Throwable(
                    message = "This presenter doesn't have a view attached to it",
                    cause = InstantiationException())
        }
    }

    abstract fun unsubscribe()

}