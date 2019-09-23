package br.com.cavreti.desafio_android.network

interface RequestCallback {
    fun onError(throwable: Throwable)
    fun onSuccess(response: Any?)
}