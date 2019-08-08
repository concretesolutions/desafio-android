package br.com.eriberto.desafioandroidconcrete.model.interfaces

import br.com.eriberto.desafioandroidconcrete.model.pojo.ForkRepositorio

interface ForkSearchView {
    fun showProgress()
    fun hideProgress()
    fun showResult(result: ArrayList<ForkRepositorio>)
    fun showErroResult(mensagem: String?)
}