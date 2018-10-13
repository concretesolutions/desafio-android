package com.rafaelpereiraramos.desafioAndroid.core

import com.rafaelpereiraramos.desafioAndroid.App
import com.rafaelpereiraramos.desafioAndroid.di.ActivityContributorModule
import com.rafaelpereiraramos.desafioAndroid.di.AppModule
import com.rafaelpereiraramos.desafioAndroid.di.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * Created by Rafael P. Ramos on 12/10/2018.
 */
@Singleton
@Component(modules = [AndroidInjectionModule::class, AppModule::class, NetworkModule::class, ActivityContributorModule::class])
interface AppComponent {
    @Component.Builder interface Builder {
        @BindsInstance
        fun application(application: App): Builder

        fun build(): AppComponent
    }

    fun inject(application: App)
}