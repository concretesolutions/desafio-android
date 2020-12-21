package com.ccortez.desafioconcrete.factory

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import com.ccortez.desafioconcrete.data.local.dao.ItemDao
import com.ccortez.desafioconcrete.data.remote.ApiService
import com.ccortez.desafioconcrete.data.repository.ItemDataSource
import com.ccortez.desafioconcrete.model.ItemEntity
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class ItemDataSourceFactory @Inject constructor(
    private val itemDao: ItemDao,
    private val scope: CoroutineScope,
    private val apiService: ApiService
) : DataSource.Factory<Int, ItemEntity>() {

    private val liveData = MutableLiveData<ItemDataSource>()

    override fun create(): DataSource<Int, ItemEntity> {
        val source = ItemDataSource(
            apiService,
            itemDao,
            scope
        )
        liveData.postValue(source)
        return source
    }

    companion object {
        private const val PAGE_SIZE = 5

        fun pagedListConfig() = PagedList.Config.Builder()
            .setInitialLoadSizeHint(PAGE_SIZE)
            .setPageSize(PAGE_SIZE)
            .setEnablePlaceholders(true)
            .build()
    }
}