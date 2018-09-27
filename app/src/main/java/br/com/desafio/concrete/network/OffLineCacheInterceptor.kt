package br.com.desafio.concrete.network

import android.content.Context
import br.com.desafio.concrete.network.ApiService.Companion.HEADER_CACHE_CONTROL
import br.com.desafio.concrete.network.ApiService.Companion.HEADER_PRAGMA
import br.com.desafio.concrete.utils.NetworkUtils
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import java.util.concurrent.TimeUnit


class OffLineCacheInterceptor(private val context: Context?) : Interceptor  {

    override fun intercept(chain: Interceptor.Chain?): Response? {

        var request = chain?.request()

        if(context != null && !NetworkUtils.isInternetAvailable(context)){
            val cacheControl = CacheControl.Builder()
                    .maxStale(7, TimeUnit.DAYS)
                    .build()

            request = request?.newBuilder()
                    ?.removeHeader(HEADER_PRAGMA)
                    ?.removeHeader(HEADER_CACHE_CONTROL)
                    ?.cacheControl(cacheControl)
                    ?.build()

        }

        return chain?.proceed(request)
    }

}