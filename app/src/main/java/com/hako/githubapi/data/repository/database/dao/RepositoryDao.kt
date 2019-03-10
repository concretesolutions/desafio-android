package com.hako.githubapi.data.repository.database.dao

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
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

    @Query("SELECT * FROM repositories WHERE id = :id")
    fun findRepoById(id: String): LiveData<Repository>

    @Query("SELECT * FROM repositories ORDER BY stars DESC")
    fun getPage(): DataSource.Factory<Int, Repository>

    @get:Query("SELECT * FROM repositories ORDER BY stars DESC")
    val all: List<Repository>

}
