package br.com.eriberto.desafioandroidconcrete.model.retrofit.services

import br.com.eriberto.desafioandroidconcrete.model.pojo.RepositorioDAO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ForksService {

    @GET("repos/{criador}/{repositorio}/pulls")
    fun getRepositorios(
        @Path("criador") nomeProprietario: String,
        @Path("repositorio") nomeRepositorio: String
    ): Call<RepositorioDAO>
}