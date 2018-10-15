package com.rafaelpereiraramos.desafioAndroid.di

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.rafaelpereiraramos.desafioAndroid.App
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * Created by Rafael P. Ramos on 12/10/2018.
 */
@Module(includes = [ViewModelModule::class, AndroidInjectionModule::class, ActivityContributorModule::class])
class AppModule {

    @Provides
    @Singleton
    fun provideContext(application: App): Context = application

    @Singleton
    @Provides
    fun provideSharedPreferences(context: Context): SharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(context)
}