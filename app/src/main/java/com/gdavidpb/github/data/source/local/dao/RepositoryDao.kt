package com.gdavidpb.github.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gdavidpb.github.data.model.database.RepositoryEntity

@Dao
interface RepositoryDao {
    @Query("SELECT * FROM repositories WHERE page = :page")
    suspend fun getRepositories(page: Int): List<RepositoryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveRepositories(vararg repositories: RepositoryEntity)
}