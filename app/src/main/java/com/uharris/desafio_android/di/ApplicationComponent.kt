package com.uharris.desafio_android.di

import android.app.Application
import com.uharris.desafio_android.di.modules.ApplicationModule
import com.uharris.desafio_android.di.modules.DataModule
import com.uharris.desafio_android.di.modules.PresentationModule
import com.uharris.desafio_android.di.modules.UIModule
import com.uharris.desafio_android.presentation.base.ChallengeAndroidApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidInjectionModule::class,
        UIModule::class,
        DataModule::class,
        PresentationModule::class,
        ApplicationModule::class])
interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    fun inject(app: ChallengeAndroidApplication)
}