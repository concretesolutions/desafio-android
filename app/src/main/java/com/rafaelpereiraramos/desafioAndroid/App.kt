package com.rafaelpereiraramos.desafioAndroid

import android.app.Activity
import android.app.Application
import com.rafaelpereiraramos.desafioAndroid.di.AppInjector
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

/**
 * Created by Rafael P. Ramos on 12/10/2018.
 */
class App : Application(), HasActivityInjector {

    companion object {
        const val URL_BASE = "https://api.github.com/"
        const val DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS"
        const val NETWORK_SNAPSHOT_SIZE = 15
        const val LOCAL_SNAPSHOT_SIZE = 15
    }

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        AppInjector.inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityInjector
    }
}