package com.example.lucasdias.mvvmpoc.data.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.example.lucasdias.mvvmpoc.domain.entity.Repository

@Dao
interface RepositoryDao {

    @Query("SELECT * FROM repository ORDER BY stargazers_count DESC")
    fun getRepositoryList(): LiveData<List<Repository>>

    @Query("SELECT * FROM repository WHERE page_number =:page ORDER BY stargazers_count DESC")
    fun getRepositoryListPage(page: Int): LiveData<List<Repository>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRepositoryList(repositoryList: List<Repository>?)

}