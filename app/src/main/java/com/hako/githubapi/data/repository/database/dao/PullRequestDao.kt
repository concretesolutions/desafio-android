package com.hako.githubapi.data.repository.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hako.githubapi.domain.entities.PullRequest

@Dao
interface PullRequestDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(entity: PullRequest)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAll(entities: List<PullRequest>)

    @Query("SELECT * FROM pull_requests WHERE id = :id")
    fun findPRById(id: String): LiveData<PullRequest>

}
