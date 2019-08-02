package matheusuehara.github.di

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.google.gson.Gson
import matheusuehara.github.BuildConfig
import matheusuehara.github.data.network.GitHubService
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val CONNECTION_TIMEOUT = 15L
private const val READ_TIMEOUT = 30L
private const val CACHE_SIZE = (10 * 1024 * 1024).toLong()
private const val CACHE_TIME = 60 * 60 * 24

val networkModule = module {

    single<OkHttpClient> {
        val myCache = Cache(androidContext().cacheDir, CACHE_SIZE)
        var isConnected = false

        val connectivityManager = androidContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        activeNetwork?.let {
            isConnected = it.isConnected
        }

        OkHttpClient.Builder()
                .cache(myCache)
                .addInterceptor( object : Interceptor{
                    override fun intercept(chain: Interceptor.Chain): Response {
                        var request = chain.request()
                        request = if (isConnected)
                            request.newBuilder().header("Cache-Control", "public, max-age=5").build()
                        else
                            request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=$CACHE_TIME").build()
                        return chain.proceed(request)
                    }
                })
                .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .build()
    }

    single<Retrofit> {
        Retrofit
                .Builder()
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BuildConfig.API_URL)
                .client(get())
                .build()
    }

    single<GitHubService> {
        val retrofit: Retrofit = get()
        retrofit.create<GitHubService>(GitHubService::class.java)
    }
}