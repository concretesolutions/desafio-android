package com.example.gitrepositories

import android.app.Application
import com.example.gitrepositories.model.services.ConnectivityService
import com.example.gitrepositories.model.services.GitHubService
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

val appModule = module {
    single { ConnectivityService() }
    single { GitHubService.getBaseService() }
}

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            modules(appModule)
        }
    }
}