package com.rafael.fernandes.data.modules

import android.app.Application
import androidx.room.Room
import com.rafael.fernandes.data.database.RepositoryDatabase
import com.rafael.fernandes.data.database.RepositoryDatabase.Companion.DATA_BASE_NAME
import com.rafael.fernandes.data.database.dao.RepositoryDao
import com.rafael.fernandes.domain.repositories.IOFFLineRepository
import com.rafael.fernandes.data.database.repositories.RepositoryDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton


@Module
class RoomModule @Inject constructor(app: Application) {

    private var repositoryDatabase: RepositoryDatabase =
        Room.databaseBuilder(app, RepositoryDatabase::class.java, DATA_BASE_NAME)
            .allowMainThreadQueries()
            .build()

    @Singleton
    @Provides
    fun providesRoomDatabase(): RepositoryDatabase {
        return repositoryDatabase
    }

    @Singleton
    @Provides
    fun providesScheduleDao(repositoryDatabase: RepositoryDatabase): RepositoryDao {
        return repositoryDatabase.respositoryDao
    }


    @Singleton
    @Provides
    fun scheduleRepository(repositoryDao: RepositoryDao): IOFFLineRepository {
        return RepositoryDataSource(repositoryDao)
    }

}