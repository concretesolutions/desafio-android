package br.com.eriberto.desafioandroidconcrete.model.interfaces

interface RepositorioSearchModel {
    fun buscar(page: Int, searchResultListener: SearchResultListener)
}