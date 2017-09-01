package br.com.concrete.sdk.data.remote

import android.arch.lifecycle.LiveData
import br.com.concrete.sdk.BuildConfig
import br.com.concrete.sdk.data.ResponseLiveData
import br.com.concrete.sdk.data.remote.factory.LiveDataCallAdapterFactory
import br.com.concrete.sdk.data.remote.interceptor.ResponseInterceptor
import br.com.concrete.sdk.model.Page
import br.com.concrete.sdk.model.PullRequest
import br.com.concrete.sdk.model.Repo
import br.com.concrete.sdk.model.type.Order
import br.com.concrete.sdk.model.type.SortPullRequest
import br.com.concrete.sdk.model.type.SortRepo
import br.com.concrete.sdk.model.type.State
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import timber.log.Timber

internal interface GithubApi {

    @GET("search/repositories")
    fun searchRepositories(
            @Query("q") query: String = "language:Java",
            @Query("sort") @SortRepo sort: String? = null,
            @Query("order") @Order order: String? = null,
            @Query("page") page: Int,
            @Query("per_page") perPage: Int
    ): ResponseLiveData<Page<Repo>>

    @GET("repos/{creator}/{repo}/pulls")
    fun listPullRequest(
            @Path("creator") creator: String,
            @Path("repo") repo: String,
            @Query("state") @State state: String? = null,
            @Query("head") head: String? = null,
            @Query("base") base: String? = null,
            @Query("sort") @SortPullRequest sort: String? = null,
            @Query("direction") @Order order: String? = null
    ): ResponseLiveData<List<PullRequest>>

    companion object Factory {

        val instance: GithubApi by lazy {
            Retrofit.Builder()
                    .addConverterFactory(buildGson())
                    .client(buildClient())
                    .addCallAdapterFactory(LiveDataCallAdapterFactory())
                    .baseUrl(BuildConfig.BASE_URL).build().create(GithubApi::class.java)
        }

        private fun buildClient(): OkHttpClient {
            val logging = HttpLoggingInterceptor { Timber.i(it) }
                    .setLevel(Level.BODY)
            return OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .addInterceptor(ResponseInterceptor())
                    .build()
        }

        private fun buildGson(): GsonConverterFactory {
            val gson = GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                    .create()
            return GsonConverterFactory.create(gson)
        }
    }
}