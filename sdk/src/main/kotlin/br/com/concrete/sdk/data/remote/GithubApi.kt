package br.com.concrete.sdk.data.remote

import br.com.concrete.sdk.BuildConfig
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
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory.createWithScheduler
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import timber.log.Timber

internal interface GithubApi {

    @GET("search/users")
    fun searchRepositories(
            @Query("q") query: String = "language:Java",
            @Query("sort") @SortRepo sort: String? = null,
            @Query("order") @Order order: String? = null,
            @Query("page") page: Int,
            @Query("per_page") perPage: Int
    ): Observable<Response<Page<Repo>>>

    @GET("repos/{fullName}/{repo}/pulls")
    fun listPullRequest(
            @Query("fullName") fullName: String,
            @Query("state") @State state: String? = null,
            @Query("head") head: String? = null,
            @Query("base") base: String? = null,
            @Query("sort") @SortPullRequest sort: String? = null,
            @Query("direction") @Order order: String? = null
    ): Observable<List<PullRequest>>

    companion object Factory {

        private val api: GithubApi = Retrofit.Builder()
                .addCallAdapterFactory(createWithScheduler(Schedulers.io()))
                .addConverterFactory(buildGson())
                .client(buildClient())
                .baseUrl(BuildConfig.BASE_URL).build().create(GithubApi::class.java)

        fun instance() = api

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