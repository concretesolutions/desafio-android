package br.com.henriqueoliveira.desafioandroidconcrete.service.repository

interface ResultListener <T>{
    fun onSuccess(data: T?)
    fun onFailure(message: String)
}