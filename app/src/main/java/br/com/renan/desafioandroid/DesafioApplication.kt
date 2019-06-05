package br.com.renan.desafioandroid

import android.app.Application
import br.com.renan.desafioandroid.provider.NetworkProvider

class DesafioApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        init()
    }

    private fun init() {
        NetworkProvider.init()
    }
}