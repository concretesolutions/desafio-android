package com.jsouza.app

import android.app.Application
import com.jsouza.repocatalog.di.repositoryCatalogModule
import com.jsouza.repopullrequests.di.repositoryDetailModule
import com.jsouza.shared_components.di.sharedModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.logger.AndroidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.EmptyLogger
import org.koin.core.logger.Logger

@ExperimentalCoroutinesApi
class Application : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@Application)
            logger(koinLogger())
            modules(
                modules = listOf(
                    sharedModule,
                    repositoryCatalogModule,
                    repositoryDetailModule
                )
            )
        }
    }

    private fun koinLogger(): Logger = if (BuildConfig.DEBUG) AndroidLogger() else EmptyLogger()
}
