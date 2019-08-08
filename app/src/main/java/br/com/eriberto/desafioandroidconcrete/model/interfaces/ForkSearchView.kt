package br.com.eriberto.desafioandroidconcrete.model.interfaces

import br.com.eriberto.desafioandroidconcrete.model.pojo.ForkRepositorio
import br.com.eriberto.desafioandroidconcrete.model.pojo.Repositorio
import br.com.eriberto.desafioandroidconcrete.model.pojo.RepositorioDAO

interface ForkSearchView {
    fun showProgress()
    fun hideProgress()
    fun showResult(result: ArrayList<ForkRepositorio>)
    fun showErroResult(mensagem: String?)
}