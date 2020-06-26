package com.germanofilho.desafio

import android.app.Application
import com.germanofilho.network.config.AppConfig
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Desafio : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin()
        configNetworkModule()
    }

    private fun configNetworkModule(){
        AppConfig.setContext(applicationContext)
    }

    private fun startKoin(){
        startKoin {
            androidContext(this@Desafio)
        }
    }
}