package app.kelvao.javapop.network.service

import app.kelvao.javapop.network.response.RepositoriesResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface RepositoriesRestService {

    @GET("search/repositories")
    fun getRepositories(
        @Query("q") language: String,
        @Query("sort") sort: String,
        @Query("page") page: Int,
        @Query("per_page") limit: Int
    ): Observable<RepositoriesResponse>
}