package com.rafaelpereiraramos.desafioAndroid.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.rafaelpereiraramos.desafioAndroid.App
import com.rafaelpereiraramos.desafioAndroid.api.GithubService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


/**
 * Created by Rafael P. Ramos on 12/10/2018.
 */
@Module
class AppModule {

    @Provides
    @Singleton
    public fun provideGithubService(gson: Gson): GithubService {
        val builder = Retrofit.Builder()
                .baseUrl(App.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

        return builder.create(GithubService::class.java)
    }

    @Provides
    @Singleton
    public fun provideGson(): Gson {
        return GsonBuilder()
                .setDateFormat(App.DATE_FORMAT)
                .create()
    }
}