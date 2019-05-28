package com.jmc.desafioandroidkotlin.data.dataSource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jmc.desafioandroidkotlin.data.model.database.PullEntity

@Dao
interface PullDao {
    @Query("SELECT * FROM pullRepositorios WHERE repository = :repository")
    suspend fun getPulls(repository: String): List<PullEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePulls(vararg pulls: PullEntity)
}