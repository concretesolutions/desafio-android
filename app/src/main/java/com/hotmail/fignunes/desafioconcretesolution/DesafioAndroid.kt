package com.hotmail.fignunes.desafioconcretesolution

import android.app.Application
import com.facebook.stetho.Stetho
import com.hotmail.fignunes.desafioconcretesolution.di.ActionsModule
import com.hotmail.fignunes.desafioconcretesolution.di.PresenterModule
import com.hotmail.fignunes.desafioconcretesolution.repository.di.RepositoryModule
import net.danlew.android.joda.JodaTimeAndroid
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DesafioAndroid : Application() {

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
        JodaTimeAndroid.init(this)
        startKoin {
            androidContext(this@DesafioAndroid)
            modules(
                PresenterModule.presenterModule,
                ActionsModule.actionsModule,
                RepositoryModule.repositoryModule
            )
        }
    }
}