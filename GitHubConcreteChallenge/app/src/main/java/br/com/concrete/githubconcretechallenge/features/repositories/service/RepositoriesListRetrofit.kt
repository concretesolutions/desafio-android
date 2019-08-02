package br.com.concrete.githubconcretechallenge.features.repositories.service

import br.com.concrete.githubconcretechallenge.Constants
import br.com.concrete.githubconcretechallenge.features.repositories.model.RepositoriesListResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RepositoriesListRetrofit {

    @GET("search/repositories")
    fun getRepositoriesList(
        @Query("q") query: String = "language:Java",
        @Query("sort") sort: String = "stars",
        @Query("page") page: Int,
        @Query("per_page") itemsPerPage: Int = Constants.ITEMS_PER_PAGE
    ) : Single<RepositoriesListResponse>

}
