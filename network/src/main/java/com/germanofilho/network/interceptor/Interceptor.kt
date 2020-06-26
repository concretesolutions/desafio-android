package com.germanofilho.network.interceptor

import okhttp3.CacheControl
import okhttp3.Interceptor
import java.util.concurrent.TimeUnit

val networkCacheInterceptor = Interceptor { chain ->
    val response = chain.proceed(chain.request())

    val cacheControl = CacheControl.Builder()
        .maxAge(1, TimeUnit.MINUTES)
        .build()

    response.newBuilder()
        .header("Cache-Control", cacheControl.toString())
        .build()
}
