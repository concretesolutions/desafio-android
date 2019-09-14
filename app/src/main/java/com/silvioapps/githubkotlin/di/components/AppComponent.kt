package com.silvioapps.githubkotlin.di.components

import android.app.Application
import com.silvioapps.githubkotlin.di.applications.App
import com.silvioapps.githubkotlin.di.modules.AppModule
import javax.inject.Singleton
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Singleton
@Component(modules = [AndroidInjectionModule::class, AppModule::class, AndroidSupportInjectionModule::class])
interface AppComponent: AndroidInjector<Any>{

    @Component.Builder
    interface Builder{
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }

    fun inject(application: App)
}
