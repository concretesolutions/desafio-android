package br.com.eriberto.desafioandroidconcrete.model.retrofit.services

import br.com.eriberto.desafioandroidconcrete.model.pojo.RepositorioDAO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RepositoriosService {

    @GET("search/repositories?q=language:Java&sort=stars")//"search/repositories?q=language:Java&sort=stars&page=1"
    fun getRepositorios(
        @Query("page") page: Int
    ): Call<RepositorioDAO>
}