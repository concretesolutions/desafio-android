package br.com.desafio.concrete.network

import android.content.Context
import br.com.desafio.concrete.network.ApiService.Companion.HEADER_CACHE_CONTROL
import br.com.desafio.concrete.network.ApiService.Companion.HEADER_PRAGMA
import br.com.desafio.concrete.utils.NetworkUtils
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import java.util.concurrent.TimeUnit


class CacheInterceptor(private val context: Context) : Interceptor {

    override fun intercept(chain: Interceptor.Chain?): Response? {

        val response = chain?.proceed(chain.request())

        val cacheControl: CacheControl = if(NetworkUtils.isInternetAvailable(context)){
            CacheControl.Builder()
                    .maxAge(0, TimeUnit.SECONDS)
                    .build()
        }else{
            CacheControl.Builder()
                    .maxStale(7, TimeUnit.DAYS)
                    .build()
        }

        return response?.newBuilder()
               ?.removeHeader(HEADER_PRAGMA)
               ?.removeHeader(HEADER_CACHE_CONTROL)
               ?.header(HEADER_CACHE_CONTROL, cacheControl.toString())
               ?.build()
    }

}