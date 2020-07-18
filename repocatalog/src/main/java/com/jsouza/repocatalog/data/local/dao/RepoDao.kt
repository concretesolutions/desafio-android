package com.jsouza.repocatalog.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jsouza.repocatalog.data.local.entity.RepositoryEntity

@Dao
interface RepoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(repos: List<RepositoryEntity>)

    @Query("SELECT * FROM REPOSITORIES ORDER BY stargazersCount DESC")
    fun getRepos(): PagingSource<Int, RepositoryEntity>

    @Query("DELETE FROM REPOSITORIES")
    suspend fun clearRepos()
}
