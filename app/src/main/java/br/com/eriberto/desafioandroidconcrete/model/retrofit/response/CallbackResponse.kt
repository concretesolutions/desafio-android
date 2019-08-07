package br.com.eriberto.desafioandroidconcrete.model.retrofit.response

interface CallbackResponse<T> {
    fun success(response: T)

    fun failure(response: String?)
}