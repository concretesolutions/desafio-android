package com.concrete.desafio

import android.app.Application
import com.concrete.desafio.di.ModulosDeDependencias
import org.koin.android.ext.android.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin(
            this,
            listOf(
                ModulosDeDependencias.moduloDeRepositorios,
                ModulosDeDependencias.moduloDePullRequest
                )
            )
    }
}