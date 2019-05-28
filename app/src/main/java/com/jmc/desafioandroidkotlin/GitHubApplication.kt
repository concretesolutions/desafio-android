package com.jmc.desafioandroidkotlin

import android.app.Application
import com.jmc.desafioandroidkotlin.di.appModule

import org.koin.android.ext.android.startKoin

open class GitHubApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin(this, listOf(appModule))
    }
}