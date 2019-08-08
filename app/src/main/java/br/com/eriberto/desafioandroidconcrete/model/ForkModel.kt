package br.com.eriberto.desafioandroidconcrete.model

import br.com.eriberto.desafioandroidconcrete.model.interfaces.ForkSearchModel
import br.com.eriberto.desafioandroidconcrete.model.interfaces.ForkSearchResultListener
import br.com.eriberto.desafioandroidconcrete.model.interfaces.RepositorioSearchModel
import br.com.eriberto.desafioandroidconcrete.model.interfaces.SearchResultListener
import br.com.eriberto.desafioandroidconcrete.model.pojo.ForkRepositorio
import br.com.eriberto.desafioandroidconcrete.model.pojo.RepositorioDAO
import br.com.eriberto.desafioandroidconcrete.model.retrofit.response.CallbackResponse
import br.com.eriberto.desafioandroidconcrete.model.retrofit.webClient.ForkWebClient
import br.com.eriberto.desafioandroidconcrete.model.retrofit.webClient.RepositorioWebClient

object ForkModel: ForkSearchModel {
    override fun buscar(nomeProprietario:String, nomeRepositorio:String, forkSearchResultListener: ForkSearchResultListener) {

        ForkWebClient().getForks(nomeProprietario = nomeProprietario,nomeRepositorio = nomeRepositorio,  callbackResponse = object :CallbackResponse<ArrayList<ForkRepositorio>>{
            override fun success(response: ArrayList<ForkRepositorio>) {
                forkSearchResultListener.onSearchResult(result = response)
            }

            override fun failure(response: String?) {
                forkSearchResultListener.onSearchErro(response!!)
            }

        })
    }
}