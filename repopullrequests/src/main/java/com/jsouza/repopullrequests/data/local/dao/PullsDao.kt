package com.jsouza.repopullrequests.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jsouza.repopullrequests.data.local.entity.PullsEntity

@Dao
interface PullsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg repos: PullsEntity)

    @Query("SELECT * FROM PULL_REQUESTS_TABLE WHERE repositoryId = :repositoryId")
    fun getPullRequests(repositoryId: Long): LiveData<List<PullsEntity>?>

    @Query("SELECT * FROM PULL_REQUESTS_TABLE WHERE repositoryId = :repositoryId")
    fun getPullRequestsAsMutableList(repositoryId: Long): MutableList<PullsEntity>
}
