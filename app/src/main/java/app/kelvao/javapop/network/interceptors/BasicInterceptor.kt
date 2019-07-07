package app.kelvao.javapop.network.interceptors

import okhttp3.Interceptor
import okhttp3.Response

class BasicInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val builder = request.newBuilder()
            .addHeader("Content-Type", "application/json")
        return chain.proceed(builder.build())
    }
}