package br.com.eriberto.desafioandroidconcrete.model.retrofit.services

import br.com.eriberto.desafioandroidconcrete.model.pojo.ForkRepositorio
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ForksService {

    @GET("repos/{criador}/{repositorio}/pulls")
    fun getForks(
        @Path("criador") nomeProprietario: String,
        @Path("repositorio") nomeRepositorio: String
    ): Call<ArrayList<ForkRepositorio>>
}