package br.com.eriberto.desafioandroidconcrete.model

import br.com.eriberto.desafioandroidconcrete.model.interfaces.RepositorioSearchModel
import br.com.eriberto.desafioandroidconcrete.model.interfaces.SearchResultListener
import br.com.eriberto.desafioandroidconcrete.model.pojo.RepositorioDAO
import br.com.eriberto.desafioandroidconcrete.model.retrofit.response.CallbackResponse
import br.com.eriberto.desafioandroidconcrete.model.retrofit.webClient.RepositorioWebClient

object RepositorioModel: RepositorioSearchModel {
    override fun buscar(page: Int, searchResultListener: SearchResultListener) {

        RepositorioWebClient().getRepositorios(page = page, callbackResponse = object :CallbackResponse<RepositorioDAO>{
            override fun success(response: RepositorioDAO) {
                searchResultListener.onSearchResult(result = response)
            }

            override fun failure(response: String?) {
                searchResultListener.onSearchErro(response!!)
            }

        })
    }
}