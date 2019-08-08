package br.com.eriberto.desafioandroidconcrete.model.interfaces

import br.com.eriberto.desafioandroidconcrete.model.pojo.RepositorioDTO

interface SearchResultListener {
    fun onSearchResult(result: RepositorioDTO)

    fun onSearchErro(mensagem:String)
}