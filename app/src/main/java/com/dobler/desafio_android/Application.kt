package com.dobler.desafio_android

import android.app.Application
import com.dobler.desafio_android.di.AppModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class Application : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            printLogger()
            androidContext(this@Application)
            modules(
                listOf(
                    AppModule.rxModule,
                    AppModule.vieModelModule,
                    AppModule.apiModule,
                    AppModule.repositoriesModule
                )
            )

        }

    }

}