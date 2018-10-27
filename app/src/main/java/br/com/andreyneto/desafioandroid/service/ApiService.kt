package br.com.andreyneto.desafioandroid.service

import br.com.andreyneto.desafioandroid.model.Pull
import br.com.andreyneto.desafioandroid.model.RepoResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

class ApiService {
    private val BASE_URL = "https://api.github.com/"

    fun getApi(): GitHubApi {
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        return retrofit.create(GitHubApi::class.java)
    }

    interface GitHubApi {
        @GET("search/repositories")
        fun repos(@Query("page") page: Int,
                  @Query("sort") sort: String = "stars",
                  @Query("q") q: String = "language:Java"): Call<RepoResponse>

        @GET("repos/{owner}/{repo}/pulls")
        fun pulls(@Path("owner") owner: String,
                  @Path("repo") repo: String) : Call<List<Pull>>
    }
}