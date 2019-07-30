package wilquer.lima.desafioconcrete.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import wilquer.lima.desafioconcrete.data.response.RepositoryResponse

interface RepositoryService{

    @GET("/search/repositories?q=language:Java&sort=stars&{page}")
    fun getRepositories(@Path("page") page: Int): Call<RepositoryResponse>
}