package com.example.desafio_android.di

import com.example.desafio_android.data.repositories.DetailRepository
import com.example.desafio_android.data.repositories.HomeRepository
import com.example.desafio_android.data.repositories.source.RemoteApiSource
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun provideRemoteApiSource(retrofit: Retrofit): RemoteApiSource =
        retrofit.create((RemoteApiSource::class.java))

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(OkHttpClient.Builder().build())
            .build()
    }

    @Provides
    fun provideHomeRepository(
        remoteApiSource: RemoteApiSource
    ) : HomeRepository =  HomeRepository(remoteApiSource)

    @Provides
    fun provideDetailRepository(
        remoteApiSource: RemoteApiSource
    ) : DetailRepository = DetailRepository(remoteApiSource)
}