package br.com.eriberto.desafioandroidconcrete.model.retrofit.webClient

import br.com.eriberto.desafioandroidconcrete.model.pojo.RepositorioDAO
import br.com.eriberto.desafioandroidconcrete.model.retrofit.RetrofitConfig
import br.com.eriberto.desafioandroidconcrete.model.retrofit.response.CallbackResponse
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositorioWebClient {
    fun getRepositorios(page:Int, callbackResponse: CallbackResponse<RepositorioDAO>) {

        val call = RetrofitConfig().repositoriosService().getRepositorios(page = page, q = "language:Java", sort = "stars")

        call.enqueue(object : Callback<RepositorioDAO> {
            override fun onResponse(call: Call<RepositorioDAO>, response: Response<RepositorioDAO>) {

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

            override fun onFailure(call: Call<RepositorioDAO>, t: Throwable) {

                callbackResponse.failure(t.message.toString())
            }

        })
    }
}