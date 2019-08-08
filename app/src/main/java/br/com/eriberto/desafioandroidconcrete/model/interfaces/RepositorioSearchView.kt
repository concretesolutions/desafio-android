package br.com.eriberto.desafioandroidconcrete.model.interfaces

import br.com.eriberto.desafioandroidconcrete.model.pojo.Repositorio
import br.com.eriberto.desafioandroidconcrete.model.pojo.RepositorioDAO

interface RepositorioSearchView {
    fun showProgress()
    fun hideProgress()
    fun showResult(result: RepositorioDAO)
    fun showErroResult(mensagem: String?)
}