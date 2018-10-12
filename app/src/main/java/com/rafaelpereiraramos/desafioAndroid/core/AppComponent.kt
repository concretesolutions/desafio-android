package com.rafaelpereiraramos.desafioAndroid.core

import com.rafaelpereiraramos.desafioAndroid.App
import com.rafaelpereiraramos.desafioAndroid.di.AppModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Rafael P. Ramos on 12/10/2018.
 */
@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    @Component.Builder interface Builder {
        @BindsInstance
        fun application(application: App): Builder

        fun build(): AppComponent
    }
}