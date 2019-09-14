package com.silvioapps.githubkotlin.di.applications

import com.silvioapps.githubkotlin.di.components.DaggerAppComponent
import com.silvioapps.githubkotlin.features.shared.views.applications.CustomApplication
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class App : CustomApplication(), HasAndroidInjector{
    @Inject lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
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
