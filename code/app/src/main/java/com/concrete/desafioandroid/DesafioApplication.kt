package com.concrete.desafioandroid

import android.app.Application
import com.concrete.desafioandroid.di.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.androidModule


class DesafioApplication: Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidModule(this@DesafioApplication))
        import(networkModule)
        import(dataSourceModule)
        import(repositoryModule)
        import(interactorModule)
        import(presenterModule)
    }

}