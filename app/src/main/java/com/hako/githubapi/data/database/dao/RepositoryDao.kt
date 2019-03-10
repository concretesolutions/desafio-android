package com.hako.githubapi.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hako.githubapi.domain.entities.Repository

@Dao
interface RepositoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(entity: Repository)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAll(entities: List<Repository>)

    @get:Query("SELECT * FROM repositories ORDER BY stars DESC")
    val all: List<Repository>

    @Query("SELECT * FROM repositories WHERE page = :page ORDER BY stars DESC")
    fun getPage(page: Int): List<Repository>

    @Query("SELECT COUNT(*) FROM repositories WHERE page = :page")
    fun count(page: Int): Int

}
