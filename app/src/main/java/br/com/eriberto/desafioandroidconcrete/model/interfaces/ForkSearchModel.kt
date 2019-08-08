package br.com.eriberto.desafioandroidconcrete.model.interfaces

interface ForkSearchModel {
    fun buscar(nomeProprietario:String, nomeRepositorio:String, forkSearchResultListener: ForkSearchResultListener)
}