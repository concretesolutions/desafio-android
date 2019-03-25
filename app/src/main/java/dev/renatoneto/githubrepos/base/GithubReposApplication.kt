package dev.renatoneto.githubrepos.base

import android.app.Application
import android.util.Log
import dev.renatoneto.githubrepos.di.AppModule.apiModule
import dev.renatoneto.githubrepos.di.AppModule.appModule
import dev.renatoneto.githubrepos.di.AppModule.rxModule
import dev.renatoneto.githubrepos.di.AppModule.dataSourceModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class GithubReposApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Log.d("GithubReposApplication", "GithubReposApplication")

        startKoin {

            androidContext(this@GithubReposApplication)
            modules(listOf(apiModule, dataSourceModule, rxModule, appModule))

        }

    }

}