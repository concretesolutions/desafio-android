package com.ccortez.desafioconcreteapplication.di

import android.app.Application
import com.ccortez.desafioconcreteapplication.MVVMApplication
import com.ccortez.desafioconcreteapplication.di.module.AppModule
import com.ccortez.desafioconcreteapplication.di.module.MainActivityModule
import com.ccortez.desafioconcreteapplication.di.module.ViewModelFactoryModule
import com.ccortez.desafioconcreteapplication.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import javax.inject.Singleton

@Singleton
@Component(modules = [(AndroidInjectionModule::class), (AppModule::class),
    (ViewModelModule::class), (MainActivityModule::class),
    (ViewModelFactoryModule::class)])
interface AppComponent : AndroidInjector<DaggerApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: MVVMApplication): Builder

        fun build(): AppComponent
    }

    fun inject(mvvmApplication: MVVMApplication)

    override fun inject(instance: DaggerApplication?)
}