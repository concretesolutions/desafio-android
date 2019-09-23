package br.com.cavreti.desafio_android.network.service

import br.com.cavreti.desafio_android.network.response.RepositoryResponse
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

interface RepositoryService {

    @GET("search/repositories?q=language:Java&sort=stars")
    fun getRepositories(@Query("page") page : Int) : Observable<RepositoryResponse>
}