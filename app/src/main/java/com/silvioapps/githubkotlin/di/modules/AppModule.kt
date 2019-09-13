package com.silvioapps.githubkotlin.di.modules

import android.app.Application
import android.content.Context
import javax.inject.Singleton
import dagger.Module
import dagger.Provides

@Module(includes = [ActivitiesModule::class, FragmentsModule::class])
class AppModule{

    @Provides
    @Singleton
    fun providesContext(obj: Application): Context{
        return obj
    }
}

