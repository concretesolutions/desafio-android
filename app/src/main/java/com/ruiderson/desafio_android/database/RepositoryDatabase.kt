package com.ruiderson.desafio_android.database

import androidx.room.*
import com.ruiderson.desafio_android.models.RepositoryCache

@Database(entities = arrayOf(RepositoryCache::class), version = 1, exportSchema = false)
abstract class RepositoryDatabase : RoomDatabase() {
    companion object{
        val DATABASE_NAME = "Repository"
    }
    abstract fun repositoryDao(): RepositoryDao
}

@Dao
interface RepositoryDao {

    @Query("SELECT * FROM RepositoryCache ORDER BY ordem ASC ")
    fun getAll(): List<RepositoryCache>

    @Insert
    fun insert(users: RepositoryCache)

    @Query("DELETE FROM RepositoryCache")
    fun deleteAll()
}