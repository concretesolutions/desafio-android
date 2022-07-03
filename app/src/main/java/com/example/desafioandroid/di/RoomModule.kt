package com.example.desafioandroid.di

import android.content.Context
import androidx.room.Room
import com.example.desafioandroid.data.database.RepoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val PULL_DATABASE_NAME = "repo_database"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) = Room.databaseBuilder(context, RepoDatabase::class.java,PULL_DATABASE_NAME).build()

    @Singleton
    @Provides
    fun providePullDao(db: RepoDatabase) = db.getRepoDao()

}