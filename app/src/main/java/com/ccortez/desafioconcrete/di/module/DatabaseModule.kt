package com.ccortez.desafioconcrete.di.module

import android.app.Application
import com.ccortez.desafioconcrete.data.local.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun providesDatabase(application: Application) = AppDatabase.invoke(application)

    @Singleton
    @Provides
    fun providesItemDao(database: AppDatabase) = database.itemDao()
}