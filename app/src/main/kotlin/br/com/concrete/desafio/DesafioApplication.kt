package br.com.concrete.desafio

import android.app.Application
import timber.log.Timber

class DesafioApplication : android.app.Application() {

    override fun onCreate() {
        super.onCreate()
        if (br.com.concrete.BuildConfig.DEBUG)
            timber.log.Timber.plant(timber.log.Timber.DebugTree())
    }

}