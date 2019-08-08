package br.com.eriberto.desafioandroidconcrete.model.retrofit.webClient

import br.com.eriberto.desafioandroidconcrete.model.pojo.RepositorioDTO
import br.com.eriberto.desafioandroidconcrete.model.retrofit.RetrofitConfig
import br.com.eriberto.desafioandroidconcrete.model.retrofit.response.CallbackResponse
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositorioWebClient {
    fun getRepositorios(page:Int, callbackResponse: CallbackResponse<RepositorioDTO>) {

        val call = RetrofitConfig().repositoriosService().getRepositorios(page = page)

        call.enqueue(object : Callback<RepositorioDTO> {
            override fun onResponse(call: Call<RepositorioDTO>, response: Response<RepositorioDTO>) {

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

            override fun onFailure(call: Call<RepositorioDTO>, t: Throwable) {

                callbackResponse.failure(t.message.toString())
            }

        })
    }
}