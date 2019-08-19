package dev.theuzfaleiro.trendingongithub.network.di

import dagger.Module
import dagger.Provides
import dev.theuzfaleiro.trendingongithub.BuildConfig
import dev.theuzfaleiro.trendingongithub.network.GitHubEndpoint
import io.appflate.restmock.RESTMockServer
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
object RetrofitTestModule {

    @JvmStatic
    @Provides
    @Singleton
    fun providesOpenWeatherEndPoint(retrofit: Retrofit): GitHubEndpoint =
        retrofit.create(GitHubEndpoint::class.java)

    @JvmStatic
    @Provides
    @Singleton
    fun providesRetrofit(
        okHttpClient: OkHttpClient, gsonConverterFactory: GsonConverterFactory,
        rxJava2CallAdapterFactory: RxJava2CallAdapterFactory
    ): Retrofit =
        Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(rxJava2CallAdapterFactory)
            .baseUrl(RESTMockServer.getUrl())
            .build()

    @JvmStatic
    @Provides
    @Singleton
    fun providesOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .sslSocketFactory(
                RESTMockServer.getSSLSocketFactory(),
                RESTMockServer.getTrustManager()
            )
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()


    @JvmStatic
    @Provides
    fun providesMoshiConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @JvmStatic
    @Provides
    fun providesRxCallAdapterFactory(): RxJava2CallAdapterFactory =
        RxJava2CallAdapterFactory.create()

    @JvmStatic
    @Provides
    fun providesHttpLoggingInterceptor(buildType: String): HttpLoggingInterceptor {
        return if (buildType.contentEquals("debug")) {
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        } else {
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    @JvmStatic
    @Provides
    fun providesBuildType(): String = BuildConfig.BUILD_TYPE
}