package com.ccortez.desafioconcrete.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.ccortez.desafioconcrete.data.local.dao.ItemDao
import com.ccortez.desafioconcrete.factory.ItemDataSourceFactory
import com.ccortez.desafioconcrete.factory.UserDataSourceFactory
import com.ccortez.desafioconcrete.model.ItemEntity
import com.ccortez.desafioconcrete.model.UserEntity
import com.ccortez.desafioconcrete.utils.NetworkUtils
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ItemsRepository @Inject constructor(
    private val itemsDao: ItemDao,
    val context: Context,
    private val itemDataSourceFactory: ItemDataSourceFactory
) {

    fun observePagedUsers() =
        if (NetworkUtils.isOnline(context)) {
            getDataFromRemote()
        } else {
            getDataFromLocal()
        }


    private fun getDataFromRemote(): LiveData<PagedList<ItemEntity>> {
        return LivePagedListBuilder(
            itemDataSourceFactory,
            ItemDataSourceFactory.pagedListConfig()
        ).build()
    }

    private fun getDataFromLocal(): LiveData<PagedList<ItemEntity>> {
        val dataSourceFactory = itemsDao.getItems()
        return LivePagedListBuilder(
            dataSourceFactory,
            ItemDataSourceFactory.pagedListConfig()
        ).build()
    }

}