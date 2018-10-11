package com.diegoferreiracaetano.data.repo

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.diegoferreiracaetano.domain.repo.Repo
import io.reactivex.Single

@Dao
interface RepoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(entityLocals: List<Repo>): List<Long>

    @Query("SELECT * FROM repo ORDER BY repo.starts DESC")
    fun getAll(): DataSource.Factory<Int, Repo>

    @Query("SELECT COUNT(*) FROM repo")
    fun getTotal() : Single<Int>
}
