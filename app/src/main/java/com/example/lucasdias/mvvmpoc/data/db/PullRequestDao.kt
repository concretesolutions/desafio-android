package com.example.lucasdias.mvvmpoc.data.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.example.lucasdias.mvvmpoc.domain.entity.PullRequest

@Dao
interface PullRequestDao {

    @Query("SELECT * FROM pullRequest WHERE repository_id =:repositoryId ")
    fun getPullRequestList(repositoryId: String): LiveData<List<PullRequest>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPullRequestList(repositoryList: List<PullRequest>?)

    @Query("DELETE FROM pullRequest WHERE repository_id = :repositoryId")
    fun deletePullRequestsByRepositoryId(repositoryId: String)

    @Query("SELECT * FROM pullRequest WHERE repository_id = :repositoryId")
    fun getPullRequestsMutableList(repositoryId: String): MutableList<PullRequest>
}