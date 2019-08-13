package com.rafael.fernandes.desafioconcrete.injection.components

import android.app.Application
import com.rafael.fernandes.data.modules.*
import com.rafael.fernandes.desafioconcrete.AndroidApplication
import com.rafael.fernandes.desafioconcrete.injection.modules.ActivityBindingModule
import com.rafael.fernandes.desafioconcrete.injection.modules.AppModule
import com.rafael.fernandes.desafioconcrete.injection.modules.RepositoryModule
import com.rafael.fernandes.desafioconcrete.injection.modules.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ActivityBindingModule::class,
        FactoryModule::class,
        UrlApiModule::class,
        RequestModule::class,
        NetworkModule::class,
        ViewModelModule::class,
        RepositoryModule::class,
        InterceptorModule::class,
        RoomModule::class
    ]
)

interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun api(urlApiModule: UrlApiModule): Builder

        fun roomModule(roomModule: RoomModule): Builder

        fun build(): AppComponent
    }

    fun inject(androidApplication: AndroidApplication)


    fun application(): Application

}