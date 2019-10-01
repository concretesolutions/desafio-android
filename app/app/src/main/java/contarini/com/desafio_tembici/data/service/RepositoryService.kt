package contarini.com.desafio_tembici.data.service

import contarini.com.desafio_tembici.data.models.BaseRepositoriesResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RepositoryService {

    @GET("repositories?")
    fun getIncidents(@Query("q") language : String,
                     @Query("sort") sort : String,
                     @Query("page") page : Int): Single<BaseRepositoriesResponse>
}