package wilquer.lima.desafioconcrete.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import wilquer.lima.desafioconcrete.data.response.RepositoryResponse

interface RepositoryService{

    @GET("/search/repositories?q=language:Java&sort=stars&")
    fun getRepositories(@Query("page") page: Int): Call<RepositoryResponse>
}