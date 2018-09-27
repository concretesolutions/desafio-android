package br.com.desafio.concrete.application

import android.app.Application
import br.com.desafio.concrete.di.AppModule
import org.koin.android.ext.android.startKoin

/**
 * Created by Malkes on 24/09/2018.
 */
class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin(this, listOf(AppModule.getModule(this)))
    }

}