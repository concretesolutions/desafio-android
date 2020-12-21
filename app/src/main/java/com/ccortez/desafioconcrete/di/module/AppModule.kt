package com.ccortez.desafioconcrete.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO

@Module
class AppModule {

    @Provides
    fun provideCoroutineScopeIO() = CoroutineScope(IO)

    @Provides
    fun provideAppContext(app: Application): Context =  app.applicationContext

}