package com.example.eloyvitorio.githubjavapop.application

import android.annotation.SuppressLint
import android.app.Application
import com.example.eloyvitorio.githubjavapop.di.appModule
import com.example.eloyvitorio.githubjavapop.data.network.CreateRetrofitImpl
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

@SuppressLint("App")
class App : Application() {
    private val gitApiUrl: String = "https://api.github.com/"

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(appModule)
        }

        CreateRetrofitImpl.createApi(gitApiUrl)
    }
}