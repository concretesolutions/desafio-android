package br.com.eriberto.desafioandroidconcrete.model.interfaces

import br.com.eriberto.desafioandroidconcrete.model.pojo.RepositorioDAO

interface SearchResultListener {
    fun onSearchResult(result: RepositorioDAO)

    fun onSearchErro(mensagem:String)
}