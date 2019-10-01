package contarini.com.desafio_tembici.data.interceptors

import contarini.com.desafio_tembici.NetworkConstants
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class AddBearerInterceptor : Interceptor {
    private val BEARER_AUTHENTICATION = "Authorization"

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val bearer = NetworkConstants.TOKEN
        val original = chain.request()

        val request = original.newBuilder()
            .addHeader(BEARER_AUTHENTICATION, "OAUTH-TOKEN $bearer")
            .method(original.method(), original.body())
            .build()

        return chain.proceed(request)
    }
}



