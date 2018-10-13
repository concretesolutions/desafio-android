package com.rafaelpereiraramos.desafioAndroid.database.dao

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rafaelpereiraramos.desafioAndroid.database.model.RepoTO

/**
 * Created by Rafael P. Ramos on 13/10/2018.
 */
@Dao
interface RepoDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(posts: List<RepoTO>)

    @Query("SELECT * FROM repos WHERE (name LIKE :queryString) OR (description LIKE " +
            ":queryString) ORDER BY stargazers DESC, name ASC")
    fun reposByName(queryString: String): DataSource.Factory<Int, RepoTO>
}