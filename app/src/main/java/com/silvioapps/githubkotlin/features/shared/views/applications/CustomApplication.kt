package com.silvioapps.githubkotlin.features.shared.views.applications

import android.app.Application
import com.silvioapps.githubkotlin.di.components.DaggerAppComponent
import com.silvioapps.githubkotlin.features.shared.utils.Utils
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class CustomApplication : Application(), HasAndroidInjector {
    @Inject lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()

        Utils.fixSSLError(getApplicationContext())

        DaggerAppComponent
            .builder()
            .application(this)
            .build()
            .inject(this)
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return dispatchingAndroidInjector
    }
}