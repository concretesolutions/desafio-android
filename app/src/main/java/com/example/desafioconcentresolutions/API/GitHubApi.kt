package com.example.desafioconcentresolutions.API

import com.example.desafioconcentresolutions.BuildConfig
import com.example.desafioconcentresolutions.models.GitHubPull
import com.example.desafioconcentresolutions.models.GitHubRepoPage
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface GitHubApi {
    @GET("search/repositories?q=language:Java&sort=stars")
    fun listAllRepoByPage(@Query("page") page:Int, @Query("per_page") per_page:Int): Call<GitHubRepoPage>

    @GET("/repos/{ownerName}/{repo}/pulls")
    fun listAllPRByPage(@Path("ownerName")ownerName:String, @Path("repo") repo:String, @Query("page") page:Int, @Query("per_page") per_page:Int): Call<List<GitHubPull>>

    companion object{
        fun getGitHubApi(): GitHubApi {

            val baseUrl = "https://api.github.com/"
            val clientBuilder = OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)

            if (BuildConfig.DEBUG) {
                val logger: HttpLoggingInterceptor = HttpLoggingInterceptor()
                logger.level = HttpLoggingInterceptor.Level.BODY

                clientBuilder.addInterceptor(logger)
            }

            val client = clientBuilder.build()

            val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(MoshiConverterFactory.create())
                .client(client)
                .build()

            return retrofit.create(GitHubApi::class.java)
        }
    }
}