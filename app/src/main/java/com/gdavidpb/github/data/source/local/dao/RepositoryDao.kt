package com.gdavidpb.github.data.source.local.dao

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gdavidpb.github.data.model.database.RepositoryEntity

@Dao
interface RepositoryDao {
    @Query("SELECT * FROM repositories ORDER BY stargazersCount DESC")
    fun browse(): DataSource.Factory<Int, RepositoryEntity>

    @Query("SELECT COUNT(*) FROM repositories")
    suspend fun getRepositoriesCount(): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveRepositories(vararg repositories: RepositoryEntity)
}