package br.com.bernardino.githubsearch.api

import br.com.bernardino.githubsearch.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitializer {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun reposService () : GithubApi = retrofit.create(GithubApi::class.java)
}