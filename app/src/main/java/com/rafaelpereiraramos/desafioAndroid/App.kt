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
        const val DATE_FORMAT = "YYYY-MM-DDTHH:MM:SSZ"
    }

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        AppInjector.inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return dispatchingAndroidInjector
    }
}