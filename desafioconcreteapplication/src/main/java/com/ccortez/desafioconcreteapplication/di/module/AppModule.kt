package com.ccortez.desafioconcreteapplication.di.module

import android.content.Context
import com.ccortez.desafioconcreteapplication.MVVMApplication
import com.ccortez.desafioconcreteapplication.service.ApiUrls.API_BASE_URL
import com.ccortez.desafioconcreteapplication.service.repository.BackEndService
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
internal class AppModule {

    @Singleton
    @Provides
    fun provideBackEndService(): BackEndService {
        val interceptor = HttpLoggingInterceptor()
//        interceptor.level = HttpLoggingInterceptor.Level.BODY
        interceptor.level = HttpLoggingInterceptor.Level.BASIC

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectionSpecs(Arrays.asList(
                ConnectionSpec.CLEARTEXT,
                ConnectionSpec.MODERN_TLS,
                ConnectionSpec.COMPATIBLE_TLS))
            .connectTimeout(100, TimeUnit.SECONDS)
            .readTimeout(100, TimeUnit.SECONDS)
            .build()
        return Retrofit.Builder()
//            .baseUrl(BackEndService.HTTP_API_URL)
            .baseUrl(API_BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BackEndService::class.java)
    }

    @Provides
    @Singleton
    fun provideGson() = GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()!!

    @Provides
    @Singleton
    fun provideContext(mvvmApp: MVVMApplication): Context = mvvmApp


//    @Singleton
//    @Provides
//    fun provideViewModelFactory(
//        viewModelSubComponent: ViewModelSubComponent.Builder
//    ): ViewModelProvider.Factory {
//        return CarViewModelFactory(viewModelSubComponent.build())
//    }
}