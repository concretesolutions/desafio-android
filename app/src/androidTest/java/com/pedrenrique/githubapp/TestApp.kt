package com.pedrenrique.githubapp

import com.pedrenrique.githubapp.config.MOCK_SERVER_URL
import com.pedrenrique.githubapp.core.di.API_BASE_URL
import com.pedrenrique.githubapp.core.di.ENABLE_HTTP_LOG
import com.pedrenrique.githubapp.core.di.modules.features.pullRequestModule
import com.pedrenrique.githubapp.core.di.modules.features.repositoriesModule
import com.pedrenrique.githubapp.core.di.modules.netModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class TestApp : App() {
    override fun setUp() {
        startKoin {
            androidLogger()

            androidContext(this@TestApp)

            properties(
                mapOf(
                    API_BASE_URL to MOCK_SERVER_URL,
                    ENABLE_HTTP_LOG to true
                )
            )

            modules(
                listOf(
                    netModule,
                    repositoriesModule,
                    pullRequestModule
                )
            )
        }
    }
}