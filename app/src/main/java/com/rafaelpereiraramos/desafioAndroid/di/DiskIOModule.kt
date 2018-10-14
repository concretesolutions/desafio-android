package com.rafaelpereiraramos.desafioAndroid.di

import android.content.Context
import androidx.room.Room
import com.rafaelpereiraramos.desafioAndroid.database.AppDatabase
import com.rafaelpereiraramos.desafioAndroid.database.dao.PullDAO
import com.rafaelpereiraramos.desafioAndroid.database.dao.RepoDAO
import dagger.Module
import dagger.Provides
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Singleton

/**
 * Created by Rafael P. Ramos on 13/10/2018.
 */
@Module
class DiskIOModule {

    @Provides
    @Singleton
    @Named("diskIOExecutor")
    fun provideDiskIOExecutor(): Executor = Executors.newSingleThreadExecutor()

    @Singleton
    @Provides
    fun provideDatabase(context: Context): AppDatabase =
            Room.databaseBuilder(context, AppDatabase::class.java, AppDatabase.DATABASE_NAME).fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun provideRepoDAO(db: AppDatabase): RepoDAO = db.reposDao()

    @Singleton
    @Provides
    fun providePullDAO(db: AppDatabase): PullDAO = db.pullDao()
}