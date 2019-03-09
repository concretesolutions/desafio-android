package com.hako.githubapi

import android.app.Application
import timber.log.Timber

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        setupLogger()
    }

    private fun setupLogger() {
        Timber.plant(Timber.DebugTree())
    }
}
