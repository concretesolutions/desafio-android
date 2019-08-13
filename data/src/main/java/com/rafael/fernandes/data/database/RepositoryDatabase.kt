package com.rafael.fernandes.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.rafael.fernandes.data.database.RepositoryDatabase.Companion.VERSION
import com.rafael.fernandes.data.database.dao.RepositoryDao
import com.rafael.fernandes.data.database.repositories.Converters
import com.rafael.fernandes.domain.model.Item

@Database(entities = [Item::class], version = VERSION)
@TypeConverters(Converters::class)
abstract class RepositoryDatabase : RoomDatabase() {

    abstract val respositoryDao: RepositoryDao

    companion object {
        const val VERSION = 1
        const val DATA_BASE_NAME = "repositories-db"
    }
}
