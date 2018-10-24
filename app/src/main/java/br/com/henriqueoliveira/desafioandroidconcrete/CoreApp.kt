package br.com.henriqueoliveira.desafioandroidconcrete

import android.app.Application
import br.com.henriqueoliveira.desafioandroidconcrete.di.androidModule
import br.com.henriqueoliveira.desafioandroidconcrete.di.remoteDatasourceModule
import org.koin.android.ext.android.startKoin

class CoreApp : Application(){

    override fun onCreate() {
        super.onCreate()

        startKoin(this, listOf(androidModule, remoteDatasourceModule))
    }
}