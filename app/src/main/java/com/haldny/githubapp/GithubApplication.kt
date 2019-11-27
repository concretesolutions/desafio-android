package com.haldny.githubapp

import android.app.Application
import com.haldny.githubapp.common.di.allModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin

class GithubApplication: Application() {

    init {
        instance = this
    }

    companion object {
        private var instance: GithubApplication? = null
        fun applicationContext() = instance as GithubApplication
    }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@GithubApplication)
            modules(allModule)
        }
    }
}