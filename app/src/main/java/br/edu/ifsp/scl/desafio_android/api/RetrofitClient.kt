package br.edu.ifsp.scl.desafio_android.api

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit

class RetrofitClient {

    private var retrofit: Retrofit? = null
    private val BASE_URL = "https://api.github.com"
    private val cacheSize = (5 * 1024 * 1024).toLong()

    private fun buildClient(context: Context): OkHttpClient {
        return OkHttpClient
            .Builder()
            .cache(Cache(context.cacheDir, cacheSize))
            .addInterceptor { chain ->
                var request = chain.request()
                request = if (hasNetwork(context)!!)
                    request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build()
                else
                    request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7).build()
                chain.proceed(request)
            }
            .build()
    }

    fun getClient(context: Context): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .client(buildClient(context))
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
        }
        return retrofit!!
    }

    fun hasNetwork(context: Context): Boolean? {
        var isConnected: Boolean? = false // Initial Value
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        if (activeNetwork != null && activeNetwork.isConnected)
            isConnected = true
        return isConnected
    }
}