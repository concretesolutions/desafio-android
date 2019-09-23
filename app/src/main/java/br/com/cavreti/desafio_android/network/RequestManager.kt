package br.com.cavreti.desafio_android.network


import rx.Observable
import rx.Observer
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.lang.Exception

/**
 * Possibilidade de utilizar EventBus para comunicar todas as camadas interessadas ao invés do uso de CallBacks
 */
fun createRequest(observable: Observable<*>, requestCallback: RequestCallback) {
    try {
        observable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Any> {
                override fun onCompleted() {
                }
                override fun onError(e: Throwable) {
                    requestCallback.onError(e)
                }

                override fun onNext(response : Any?) {
                    requestCallback.onSuccess(response)
                }
            })
    }catch (e: Exception) {
        requestCallback.onError(e)
    }
}