package br.com.concrete.desafio

import android.app.Application
import br.com.concrete.desafio.data.setupTimber

class DesafioApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        setupTimber(BuildConfig.DEBUG)
    }

}