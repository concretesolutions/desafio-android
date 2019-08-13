package com.rafael.fernandes.desafioconcrete.ui.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {

    abstract fun myOnCleared()

    private lateinit var mCompositeDisposable: CompositeDisposable

    override fun onCleared() {
        super.onCleared()
        myOnCleared()
    }

    fun onViewCreated() {
        this.mCompositeDisposable = CompositeDisposable()
    }

    fun onDestroyView() {
        mCompositeDisposable.dispose()
    }
}