package com.jmc.desafioandroidkotlin.data.dataSource.local.dao

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jmc.desafioandroidkotlin.data.model.database.RepositoryEntity


@Dao
interface RepositoryDao {
    @Query("SELECT * FROM repositorios ORDER BY stargazersCount DESC")
    fun browse(): DataSource.Factory<Int, RepositoryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveRepositories(vararg repositories: RepositoryEntity)
}