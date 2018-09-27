package br.com.desafio.concrete.network

import android.content.Context
import br.com.desafio.concrete.BuildConfig
import br.com.desafio.concrete.model.GitHubResponse
import br.com.desafio.concrete.model.PullRequest
import br.com.desafio.concrete.model.Repository
import io.reactivex.Observable
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


/**
 * Created by Malkes on 24/09/2018.
 */
class ApiService(private val context: Context) : ApiDataSource {

    companion object {
        const val CONNECTION_TIMEOUT = 120000L
        const val WRITE_TIMEOUT = 120000L
        const val READ_TIMEOUT = 120000L

        const val HEADER_CACHE_CONTROL = "Cache-Control"
        const val HEADER_PRAGMA = "Pragma"
    }


    private fun getRetrofit(): WSInterface {

        return createRetrofit(createOkHttpClient()).create(WSInterface::class.java)
    }


    private fun createOkHttpClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()

        if(BuildConfig.DEBUG){
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }
        val cacheSize: Long  = 10 * 1024 * 1024 // 10 MB
        return OkHttpClient.Builder()
                .cache(Cache(context.cacheDir, cacheSize))
                .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.MILLISECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.MILLISECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.MILLISECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(OffLineCacheInterceptor(context))
                .addNetworkInterceptor(CacheInterceptor(context))
                .build()
    }


    private fun createRetrofit(okHttpClient: OkHttpClient): Retrofit {

        return Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()
    }

    override fun fetchRepositories(technology: String, page: Int): Observable<GitHubResponse> {
        return getRetrofit().fetchRepositories("language:$technology", page, "stars")
    }

    override fun fetchPullRequests(repoItem: Repository): Observable<ArrayList<PullRequest>> {
        return getRetrofit().fetchPullRequests(repoItem.owner.login, repoItem.repoName)
    }
}
