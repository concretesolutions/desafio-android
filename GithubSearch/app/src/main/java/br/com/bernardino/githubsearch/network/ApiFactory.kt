package br.com.bernardino.githubsearch.network

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import okhttp3.logging.HttpLoggingInterceptor.Level.NONE

object ApiFactory {
    const val DEFAULT_TIMEOUT_SECONDS = 10L

    fun <T> create(
        webServicesApi: Class<T>,
        apiURL: String,
        debuggable: Boolean = false
    ): T {
        val logger = createLogger(debuggable)
        val httpClient = createHttpClient(logger = logger)
        val gson = createGson()
        val converter = GsonConverterFactory.create(gson)

        val retrofit = Retrofit.Builder()
            .client(httpClient)
            .addConverterFactory(converter)
            .baseUrl(apiURL)
            .build()

        return retrofit.create(webServicesApi)
    }

    private fun createLogger(debuggable: Boolean): Interceptor {
        val loggingLevel = if (debuggable) BODY else NONE
        return HttpLoggingInterceptor().apply { level = loggingLevel }
    }

    private fun createHttpClient(logger: Interceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(logger)
            .connectTimeout(DEFAULT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .readTimeout(DEFAULT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(DEFAULT_TIMEOUT_SECONDS, TimeUnit.MILLISECONDS)
            .build()
    }

    private fun createGson() = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .create()
}