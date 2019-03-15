package cl.getapps.githubjavarepos.core.remote

import cl.getapps.githubjavarepos.features.repopullrequests.data.remote.PullRequestAPI
import cl.getapps.githubjavarepos.features.repos.data.remote.ReposAPI
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object ServiceFactory {

    private const val GH_BASE_URL = "https://api.github.com/"
    private var httpClient: OkHttpClient? = null
    private var gson: Gson? = null
    private var httpLoggingInterceptor: HttpLoggingInterceptor? = null

    fun makeRepoService(isDebug: Boolean): ReposAPI {
        val okHttpClient = makeOkHttpClient(
            makeLoggingInterceptor(isDebug)
        )
        return makeReposService(
            okHttpClient,
            makeGson()
        )
    }

    fun makePullRequestsService(isDebug: Boolean): PullRequestAPI {
        val okHttpClient = makeOkHttpClient(
            makeLoggingInterceptor(isDebug)
        )
        return makePullRequestService(
            okHttpClient,
            makeGson()
        )
    }

    private fun makeReposService(okHttpClient: OkHttpClient, gson: Gson): ReposAPI {
        val retrofit = Retrofit.Builder()
            .baseUrl(GH_BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        return retrofit.create(ReposAPI::class.java)
    }

    private fun makePullRequestService(okHttpClient: OkHttpClient, gson: Gson): PullRequestAPI {
        val retrofit = Retrofit.Builder()
            .baseUrl(GH_BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        return retrofit.create(PullRequestAPI::class.java)
    }

    private fun makeOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        if (httpClient != null) return httpClient as OkHttpClient
        else {
            httpClient = OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .build()
            return httpClient as OkHttpClient
        }
    }

    private fun makeGson(): Gson {
        if (gson != null) return gson as Gson
        else {
            gson = GsonBuilder()
                .setLenient()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()
            return gson as Gson
        }
    }

    private fun makeLoggingInterceptor(isDebug: Boolean): HttpLoggingInterceptor {
        if (httpLoggingInterceptor != null) {
            return httpLoggingInterceptor as HttpLoggingInterceptor
        } else {
            httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor!!.level = if (isDebug)
                HttpLoggingInterceptor.Level.BODY
            else
                HttpLoggingInterceptor.Level.NONE

            return httpLoggingInterceptor as HttpLoggingInterceptor
        }
    }
}
