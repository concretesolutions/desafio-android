package com.ccortez.desafioconcrete.di.module

import com.ccortez.desafioconcrete.data.local.dao.ItemDao
import com.ccortez.desafioconcrete.data.remote.ApiService
import com.ccortez.desafioconcrete.factory.ItemDataSourceFactory
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope

@Module
class ItemDataSourceFactoryModule {

    @Provides
    fun provideItemDataSourceFactory(
        itemDao: ItemDao,
        scope: CoroutineScope,
        apiService: ApiService
    ) = ItemDataSourceFactory(
        itemDao,
        scope,
        apiService
    )
}