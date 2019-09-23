package com.anderson.apigithub_mvvm

import android.app.Activity
import android.app.Application
import com.anderson.apigithub_mvvm.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

/**
 * Created by anderson on 21/09/19.
 */
class AppApplication : Application(), HasActivityInjector {

    companion object {
        @get:Synchronized
        lateinit var instance: AppApplication
    }

    @Inject
    lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingActivityInjector

    override fun onCreate() {
        super.onCreate()
        instance = this

        initInjector()
    }

    private fun initInjector() {
        DaggerAppComponent.builder()
            .application(this)
            .build()
            .inject(this)
    }

}
