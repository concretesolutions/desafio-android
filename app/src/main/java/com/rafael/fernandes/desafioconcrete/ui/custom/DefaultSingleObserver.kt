package com.rafael.fernandes.desafioconcrete.ui.custom

import androidx.lifecycle.MutableLiveData
import com.rafael.fernandes.desafioconcrete.extentions.setError
import com.rafael.fernandes.desafioconcrete.extentions.setLoading
import com.rafael.fernandes.desafioconcrete.extentions.setSuccess
import com.rafael.fernandes.desafioconcrete.presentation.resources.Resource
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable

open class DefaultSingleObserver<T>(private val liveDate: MutableLiveData<Resource<T>>) : SingleObserver<T> {

    override fun onSubscribe(d: Disposable) {
        liveDate.setLoading()
    }

    override fun onSuccess(value: T) {
        liveDate.setSuccess(value)
    }

    override fun onError(e: Throwable) {
        liveDate.setError(e.message)
    }
}