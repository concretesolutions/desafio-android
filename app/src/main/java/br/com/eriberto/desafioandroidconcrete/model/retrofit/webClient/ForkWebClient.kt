package br.com.eriberto.desafioandroidconcrete.model.retrofit.webClient

import br.com.eriberto.desafioandroidconcrete.model.pojo.ForkRepositorio
import br.com.eriberto.desafioandroidconcrete.model.pojo.RepositorioDAO
import br.com.eriberto.desafioandroidconcrete.model.retrofit.RetrofitConfig
import br.com.eriberto.desafioandroidconcrete.model.retrofit.response.CallbackResponse
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ForkWebClient {
    fun getForks(nomeProprietario:String, nomeRepositorio:String, callbackResponse: CallbackResponse<ArrayList<ForkRepositorio>>) {

        val call = RetrofitConfig().forksService().getForks(nomeProprietario = nomeProprietario, nomeRepositorio = nomeRepositorio)

        call.enqueue(object : Callback<ArrayList<ForkRepositorio>> {
            override fun onResponse(call: Call<ArrayList<ForkRepositorio>>, response: Response<ArrayList<ForkRepositorio>>) {

                response.body()?.let {
                    callbackResponse.success(it)
                }

                response.errorBody().let {
                    if (it != null) {
                        val listaDeErros: JSONArray = JSONObject(it.string()).getJSONArray("errors")
                        for (i in 0 until listaDeErros.length()) {
                            callbackResponse.failure(listaDeErros[i] as String)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<ArrayList<ForkRepositorio>>, t: Throwable) {

                callbackResponse.failure(t.message.toString())
            }

        })
    }
}