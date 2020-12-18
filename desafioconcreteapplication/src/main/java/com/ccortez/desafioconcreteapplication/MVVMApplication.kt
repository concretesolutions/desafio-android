package com.ccortez.desafioconcreteapplication

import android.app.Activity
import com.ccortez.desafioconcreteapplication.di.AppComponent
import com.ccortez.desafioconcreteapplication.di.DaggerAppComponent
import com.squareup.picasso.Picasso
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

open class MVVMApplication : DaggerApplication(), HasActivityInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        initPicasso()
        DaggerAppComponent.builder().application(this)
            .build().inject(this)
    }

    open fun initPicasso() {
        Picasso.setSingletonInstance(Picasso.Builder(this).build())
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication?>? {
        val appComponent: AppComponent = DaggerAppComponent.builder().application(this).build()
        appComponent.inject(this)
        return appComponent
    }

    override fun activityInjector(): DispatchingAndroidInjector<Activity>? {
        return dispatchingAndroidInjector
    }
}