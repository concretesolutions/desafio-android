package com.pedrenrique.githubapp

import android.app.Application
import com.pedrenrique.githubapp.core.di.setUpDI

open class App : Application() {
    override fun onCreate() {
        super.onCreate()
        setUp()
    }

    open fun setUp() {
        setUpDI()
    }
}