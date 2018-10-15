package com.rafaelpereiraramos.desafioAndroid.database.dao

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rafaelpereiraramos.desafioAndroid.database.model.PullTO

/**
 * Created by Rafael P. Ramos on 14/10/2018.
 */
@Dao
interface PullDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(pulls: List<PullTO>)

    @Query("SELECT * FROM pull WHERE owner_login = :ownerLogin AND repo_name = :repoName")
    fun getPulls(ownerLogin: String, repoName: String): DataSource.Factory<Int, PullTO>
}