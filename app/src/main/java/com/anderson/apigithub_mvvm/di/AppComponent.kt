package com.anderson.apigithub_mvvm.di

import android.app.Application
import com.anderson.apigithub_mvvm.AppApplication
import com.anderson.apigithub_mvvm.di.builder.ActivityBuilder
import com.anderson.apigithub_mvvm.di.builder.ViewModelBuilder
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by anderson on 21/09/19.
 */
@Singleton
@Component(
    modules = [
        AppModule::class,
        AndroidSupportInjectionModule::class,
        ActivityBuilder::class,
        ViewModelBuilder::class
    ]
)
interface AppComponent {

    fun inject(application: AppApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}