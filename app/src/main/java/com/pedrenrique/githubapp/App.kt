package com.pedrenrique.githubapp

import android.app.Application
import com.pedrenrique.githubapp.core.di.setupDI

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        setupDI()
    }
}