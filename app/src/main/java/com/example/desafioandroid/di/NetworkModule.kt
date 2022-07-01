package com.example.desafioandroid.di

import com.example.desafioandroid.BuildConfig
import com.example.desafioandroid.data.network.RepositoriesApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    //Proveer Retrofit

    @Singleton //unica instancia de retrofit
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            //EDU Revisar cliente httpClient
            .build()
    }

    @Singleton
    @Provides
    fun provideRepositoriesApiClient(retrofit: Retrofit): RepositoriesApiClient {
        return retrofit.create(RepositoriesApiClient::class.java)
    }
}