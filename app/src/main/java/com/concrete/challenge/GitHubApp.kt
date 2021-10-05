package com.concrete.challenge

import android.app.Application
import com.concrete.challenge.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class GitHubApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@GitHubApp)
            allowOverride(true)
            modules(appModule)
        }
    }

}