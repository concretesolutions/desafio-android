package com.ccortez.desafioconcrete.di.component

import android.app.Application
import com.ccortez.desafioconcrete.GithubApp
import com.ccortez.desafioconcrete.di.builder.ActivityBuilder
import com.ccortez.desafioconcrete.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityBuilder::class,
        ViewModelModule::class,
        ViewModelFactoryModule::class,
        ApiModule::class,
        DatabaseModule::class,
        AppModule::class,
        ItemDataSourceFactoryModule::class,
        FragmentBuildersModule::class
    ]
)
interface AppComponent : AndroidInjector<GithubApp> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun create(application: Application): Builder

        fun build(): AppComponent
    }

    override fun inject(instance: GithubApp)
}