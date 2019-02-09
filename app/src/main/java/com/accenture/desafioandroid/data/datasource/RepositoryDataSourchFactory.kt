package com.accenture.desafioandroid.data.datasource

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import android.arch.paging.PageKeyedDataSource
import com.accenture.desafioandroid.mvvm.model.Item

class RepositoryDataSourchFactory : DataSource.Factory<Int, Item>() {
    var itemLiveDS= MutableLiveData<PageKeyedDataSource<Int, Item>>()


    override fun create(): DataSource<Int, Item> {
        val itemdataSource= RepositoryDataSource()
        itemLiveDS.postValue(itemdataSource)
        return itemdataSource
    }


}