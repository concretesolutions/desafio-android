package br.com.eriberto.desafioandroidconcrete.model.interfaces

import br.com.eriberto.desafioandroidconcrete.model.pojo.ForkRepositorio

interface ForkSearchResultListener {
    fun onSearchResult(result: ArrayList<ForkRepositorio>)

    fun onSearchErro(mensagem:String)
}