package com.silvioapps.githubkotlin.features.shared.services

import com.google.gson.Gson
import okhttp3.CipherSuite
import okhttp3.ConnectionSpec
import java.util.concurrent.TimeUnit
import okhttp3.OkHttpClient
import okhttp3.TlsVersion
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.KeyStore
import java.util.*
import javax.net.ssl.TrustManagerFactory
import javax.net.ssl.X509TrustManager

class ServiceGenerator {
    companion object{
        fun <T> createService(apiBaseUrl : String, timeout : Long, serviceClass : Class<T>) : T {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

            val httpClient = OkHttpClient.Builder().readTimeout(timeout, TimeUnit.SECONDS)
            httpClient?.addInterceptor(loggingInterceptor)

            val spec = ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
                .tlsVersions(TlsVersion.TLS_1_2)
                .cipherSuites(CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256,
                    CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,
                    CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256,
                    CipherSuite.TLS_ECDHE_PSK_WITH_AES_128_CBC_SHA,
                    CipherSuite.TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256)
                .build()
            val specs = ArrayList<ConnectionSpec>()
            specs.add(spec)
            specs.add(ConnectionSpec.COMPATIBLE_TLS)
            specs.add(ConnectionSpec.CLEARTEXT)
            httpClient?.connectionSpecs(specs)

            val retrofit = Retrofit.Builder()
                    .baseUrl(apiBaseUrl)
                    .addConverterFactory(GsonConverterFactory.create(Gson()))
                    .client(httpClient.build())
                    .build()

            return retrofit.create(serviceClass)
        }
    }
}
