package contarini.com.desafio_tembici.data.interceptors

import android.os.Handler
import android.os.Looper
import contarini.com.desafio_tembici.data.UserUnauthorizedBus
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class UnauthorisedInterceptor : Interceptor {
    private val UNAUTHORIZED_CODE = 401

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        if (response.code() == UNAUTHORIZED_CODE) {
            Handler(Looper.getMainLooper()).post {
                UserUnauthorizedBus.setEvent(
                    Any()
                )
            }
        }
        return response
    }
}
