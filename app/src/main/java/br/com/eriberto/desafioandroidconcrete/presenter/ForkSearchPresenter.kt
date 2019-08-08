package br.com.eriberto.desafioandroidconcrete.presenter

import br.com.eriberto.desafioandroidconcrete.model.interfaces.*
import br.com.eriberto.desafioandroidconcrete.model.pojo.ForkRepositorio

class ForkSearchPresenter(
    val view: ForkSearchView,
    val model: ForkSearchModel
) {
    fun search(nomeRepositorio: String, nomeProprietario: String) {
        view.showProgress()
        try {
            model.buscar(
                nomeRepositorio = nomeRepositorio,
                nomeProprietario = nomeProprietario,
                forkSearchResultListener = object : ForkSearchResultListener {
                    override fun onSearchErro(mensagem: String) {
                        view.showErroResult(mensagem)
                        view.hideProgress()
                    }

                    override fun onSearchResult(result: ArrayList<ForkRepositorio>) {
                        view.showResult(result)
                        view.hideProgress()
                    }

                })
        } catch (e: Exception) {
            view.hideProgress()
            view.showErroResult(e.message)
        }
    }
}