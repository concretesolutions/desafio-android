package com.ccortez.desafioconcrete

import android.app.Application
import com.ccortez.desafioconcrete.di.component.DaggerAppComponent
import com.squareup.picasso.Picasso
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class GithubApp : Application(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        initPicasso()
        DaggerAppComponent.builder().create(this).build().inject(this)
    }

    open fun initPicasso() {
        Picasso.setSingletonInstance(Picasso.Builder(this).build())
        Picasso.get().setIndicatorsEnabled(true)
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector
}