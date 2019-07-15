package com.pedrenrique.githubapp.core.di

import android.app.Application
import com.pedrenrique.githubapp.core.di.modules.features.pullRequestModule
import com.pedrenrique.githubapp.core.di.modules.features.repositoriesModule
import com.pedrenrique.githubapp.core.di.modules.netModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

fun Application.setupDI() {
    startKoin {
        androidContext(this@setupDI)
        modules(
            listOf(
                netModule,
                repositoriesModule,
                pullRequestModule
            )
        )
    }
}