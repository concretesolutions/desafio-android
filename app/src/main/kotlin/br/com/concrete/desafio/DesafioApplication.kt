package br.com.concrete.desafio

import android.app.Application
import timber.log.Timber

class DesafioApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
    }

}