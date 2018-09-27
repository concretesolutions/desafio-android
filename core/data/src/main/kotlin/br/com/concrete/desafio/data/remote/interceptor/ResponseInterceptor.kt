package br.com.concrete.desafio.data.remote.interceptor

import br.com.concrete.desafio.data.exception.BadRequestException
import br.com.concrete.desafio.data.exception.ForbiddenException
import br.com.concrete.desafio.data.exception.NotFoundException
import br.com.concrete.desafio.data.exception.SdkException
import br.com.concrete.desafio.data.exception.ServerException
import br.com.concrete.desafio.data.exception.UnauthorizedException
import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.Response
import java.io.IOException

internal class ResponseInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        val code = response.code()

        if (response.isSuccessful) return response
        val path = request.url().encodedPath()
        throw when (code) {
            500 -> ServerException("Server Exception", code, path)
            404 -> NotFoundException("Not Found Exception", code, path)
            403 -> ForbiddenException("Forbidden Exception", code, path)
            401 -> UnauthorizedException("Unauthorized Exception", code, path)
            400 -> BadRequestException("Bad Request Exception", code, path)
            else -> SdkException("Unmapped Http Exception", code, path)
        }
    }
}