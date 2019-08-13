package com.rafael.fernandes.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rafael.fernandes.domain.model.Item
import io.reactivex.Observable
import io.reactivex.Single

@Dao
abstract class RepositoryDao {
    @Query("SELECT * FROM Item WHERE id =:id")
    abstract fun getRepositoryById(id: Int): Single<Item>

    @Query("SELECT * FROM Item")
    abstract fun getAllRespositoriesSaved(): Observable<MutableList<Item>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(item: Item): Long
}