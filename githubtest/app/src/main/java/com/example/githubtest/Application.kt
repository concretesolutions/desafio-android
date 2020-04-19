package com.example.githubtest

import android.app.Application
import com.example.githubtest.di.networkModule
import com.example.githubtest.di.repositoryModule
import com.example.githubtest.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Application : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@Application)
            modules(listOf(
                viewModelModule,
                networkModule,
                repositoryModule
            ))
        }
    }
}