package br.com.bernardino.githubsearch

import br.com.bernardino.githubsearch.di.databaseModule
import br.com.bernardino.githubsearch.di.networkModule

import android.app.Application
import br.com.bernardino.githubsearch.di.dataModule
import com.facebook.stetho.Stetho
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        setupStetho()
        setupKoin()
    }

    private fun setupStetho() {
        Stetho.initializeWithDefaults(this)
    }

    private fun setupKoin() {
        startKoin {
            androidContext(this@MainApplication)
            modules(listOf(networkModule, dataModule, databaseModule))
        }
    }
}