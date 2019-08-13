package com.rafael.fernandes.data.modules

import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class FactoryModule {

    @Provides
    @Singleton
    fun provideGsonFactory(): GsonConverterFactory {
        val builder = GsonBuilder()
        return GsonConverterFactory.create(builder.create())
    }
}