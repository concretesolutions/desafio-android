package com.diegoferreiracaetano.github

import android.app.Application
import com.diegoferreiracaetano.github.di.appModule
import com.diegoferreiracaetano.github.di.dbModule
import com.diegoferreiracaetano.github.di.repositoryModule
import com.diegoferreiracaetano.github.di.viewModelModule
import org.koin.android.ext.android.startKoin


class MainApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(appModule, repositoryModule, viewModelModule, dbModule))
    }
}