package com.hako.githubapi

import android.app.Application
import com.hako.githubapi.di.database
import com.hako.githubapi.di.network
import com.hako.githubapi.di.threads
import com.hako.githubapi.di.views
import org.koin.android.ext.android.startKoin
import timber.log.Timber

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        setupLogger()
        setupDi()
    }

    private fun setupLogger() {
        Timber.plant(Timber.DebugTree())
    }

    private fun setupDi() {
        startKoin(
            this, listOf(
                network,
                threads,
                database,
                views
            )
        )
    }
}
