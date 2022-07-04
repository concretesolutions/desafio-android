package com.example.desafioandroid.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.desafioandroid.data.database.entities.RepoEntity

@Dao
interface RepoDao {

    @Query("SELECT * FROM repo_table")
    suspend fun getAllRepos(): List<RepoEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRepos(repo: List<RepoEntity>)

    @Query("DELETE FROM repo_table")
    suspend fun deleteAllRepos()
}