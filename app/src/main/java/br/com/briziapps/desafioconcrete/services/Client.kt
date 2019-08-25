package br.com.briziapps.desafioconcrete.services

import android.util.Log
import br.com.briziapps.desafioconcrete.utils.MyApplication
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.io.IOException
import java.util.concurrent.TimeUnit

class Client {

    /*companion object{

        //private const val tag: String = "ServiceGenerator"
        private const val gitHubApiBaseUrl = "https://api.github.com/"


        private const val cacheSize : Long = 10 * 1024 * 1024 // 10 MB

        private fun retrofit() : Retrofit {

            return Retrofit.Builder()
                .baseUrl(gitHubApiBaseUrl)
                .client(okHTTPClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        private fun okHTTPClient() : OkHttpClient {

            return  OkHttpClient.Builder()
                .cache(cache())
                .addInterceptor { chain ->

                    var request = chain.request()
                    request = if (MyApplication.hasNetwork())
                        request.newBuilder().header("Cache-Control","public, max-age=" + 5).build()
                    else
                        request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7).build()
                    chain.proceed(request)

                }
                .build()

        }


        private fun cache() : Cache {
            return Cache(File(MyApplication.getInstance()?.cacheDir, "identifier"), cacheSize)
        }

        fun getApi() : ApiGitHubService {
            return retrofit().create(ApiGitHubService::class.java)
        }
    }*/

    companion object{


        private const val tag: String = "ServiceGenerator"
        private const val GITHUB_API_BASE_URL = "https://api.github.com/"
        private const val HEADER_CACHE_CONTROL = "Cache-Control"
        private const val HEADER_PRAGMA = "pragma"

        const val GITHUBAPI_TOKEN = "token fe1e658547fd554b6ae889919fcc1c4a8693f2e8"


        private const val cacheSize : Long = 10 * 1024 * 1024 // 10 MB

        private fun retrofit() : Retrofit {

            return Retrofit.Builder()
                .baseUrl(GITHUB_API_BASE_URL)
                .client(okHTTPClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        private fun okHTTPClient() : OkHttpClient{

            return OkHttpClient.Builder()
                .cache(cache())
                .addInterceptor(httpLoggingInterceptor())
                .addNetworkInterceptor(networkInterceptor())
                .addInterceptor(UserAgentInterceptor("FabrizioAlmeidaFerreira"))
                .build()

        }

        private class UserAgentInterceptor(private val userAgent: String) : Interceptor {
            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain): Response {
                val originRequest = chain.request()
                val requestWithUserAgent = originRequest.newBuilder()
                    .header("User-Agent", userAgent)
                    .build()
                return chain.proceed(requestWithUserAgent)
            }
        }

        private fun cache() : Cache{
            return Cache(File(MyApplication.getInstance()?.cacheDir, "identifier"), cacheSize)
        }

        private fun offlineInterceptor(): Interceptor {
            return Interceptor { chain ->
                Log.d(tag, "offline interceptor: called.")
                var request = chain.request()

                // prevent caching when network is on. For that we use the "networkInterceptor"
                if (!MyApplication.hasNetwork()) {
                    val cacheControl = CacheControl.Builder()
                        .maxStale(7, TimeUnit.DAYS)
                        .build()

                    request = request.newBuilder()
                        .removeHeader(HEADER_PRAGMA)
                        .removeHeader(HEADER_CACHE_CONTROL)
                        .cacheControl(cacheControl)
                        .build()
                }

                chain.proceed(request)
            }
        }

        private fun networkInterceptor(): Interceptor {
            return Interceptor { chain ->
                Log.d(tag, "network interceptor: called.")

                val response = chain.proceed(chain.request())

                val cacheControl = CacheControl.Builder()
                    .maxAge(120, TimeUnit.SECONDS)
                    .build()

                response.newBuilder()
                    .removeHeader(HEADER_PRAGMA)
                    .removeHeader(HEADER_CACHE_CONTROL)
                    .header(HEADER_CACHE_CONTROL, cacheControl.toString())
                    .build()
            }
        }

        private fun httpLoggingInterceptor(): HttpLoggingInterceptor {
            val httpLoggingInterceptor =
                HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message ->
                    Log.d(tag, "log: http log: $message")
                })
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            return httpLoggingInterceptor
        }

        fun getApi() : ApiGitHubService {
            return retrofit().create(ApiGitHubService::class.java)
        }

    }

}