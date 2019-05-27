package com.losingtimeapps.javahub.application

import com.losingtimeapps.data.di.DataModule
import com.losingtimeapps.domain.di.DomainModule
import com.losingtimeapps.javahub.common.di.modules.ActivityModule
import com.losingtimeapps.javahub.ui.home.HomeActivityComponent
import dagger.BindsInstance
import dagger.Component

import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, NetworkModule::class, DataModule::class, DomainModule::class])
interface ApplicationComponent {

    fun inject(app: JavaHubApplication)

    fun createHomeActivityComponent(module: ActivityModule): HomeActivityComponent

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: JavaHubApplication): Builder

        fun build(): ApplicationComponent
    }
}
