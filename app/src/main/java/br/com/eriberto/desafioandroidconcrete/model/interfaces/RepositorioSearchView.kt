package br.com.eriberto.desafioandroidconcrete.model.interfaces

import br.com.eriberto.desafioandroidconcrete.model.pojo.RepositorioDTO

interface RepositorioSearchView {
    fun showProgress()
    fun hideProgress()
    fun showResult(result: RepositorioDTO)
    fun showErroResult(mensagem: String?)
}