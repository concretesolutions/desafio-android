package com.ccortez.desafioconcrete.data.local.dao

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ccortez.desafioconcrete.model.ItemEntity

@Dao
interface ItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItems(itemEntity: List<ItemEntity>)

    @Query("Select * from 'ItemEntity'")
    fun getItems(): DataSource.Factory<Int, ItemEntity>
}