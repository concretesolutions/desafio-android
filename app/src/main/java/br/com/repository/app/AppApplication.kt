package br.com.repository.app

import android.app.Application
import br.com.repository.service.Api
import org.koin.android.ext.android.startKoin

class AppApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(appModule))
        Api.getInstance(applicationContext)
    }

}