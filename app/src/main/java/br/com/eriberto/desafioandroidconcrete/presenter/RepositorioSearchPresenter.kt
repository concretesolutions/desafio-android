package br.com.eriberto.desafioandroidconcrete.presenter

import br.com.eriberto.desafioandroidconcrete.model.interfaces.RepositorioSearchModel
import br.com.eriberto.desafioandroidconcrete.model.interfaces.RepositorioSearchView
import br.com.eriberto.desafioandroidconcrete.model.interfaces.SearchResultListener
import br.com.eriberto.desafioandroidconcrete.model.pojo.RepositorioDTO

class RepositorioSearchPresenter(
    val view: RepositorioSearchView,
    val model:RepositorioSearchModel
) {
    fun search(page:Int){
        view.showProgress()
        try {
            model.buscar(page = page, searchResultListener =  object : SearchResultListener {
                override fun onSearchErro(mensagem: String) {
                    view.showErroResult(mensagem)
                    view.hideProgress()
                }

                override fun onSearchResult(result: RepositorioDTO) {
                    view.showResult(result)
                    view.hideProgress()
                }

            })
        }catch (e: Exception){
            view.hideProgress()
            view.showErroResult(e.message)
        }
    }
}