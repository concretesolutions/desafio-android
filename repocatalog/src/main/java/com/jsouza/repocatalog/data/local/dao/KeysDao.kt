package com.jsouza.repocatalog.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jsouza.repocatalog.data.local.entity.RepoKeysEntity

@Dao
interface KeysDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKey: List<RepoKeysEntity>)

    @Query("SELECT * FROM REPO_KEYS WHERE repositoryId = :repoId")
    suspend fun getRemoteKey(repoId: Long): RepoKeysEntity?

    @Query("DELETE FROM REPO_KEYS")
    suspend fun clearRemoteKeys()
}
