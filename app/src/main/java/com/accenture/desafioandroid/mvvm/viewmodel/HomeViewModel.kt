package com.accenture.desafioandroid.mvvm.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PageKeyedDataSource
import android.arch.paging.PagedList
import com.accenture.desafioandroid.data.datasource.RepositoryDataSourchFactory
import com.accenture.desafioandroid.mvvm.model.Item
import android.arch.lifecycle.MutableLiveData



class HomeViewModel : ViewModel() {
    var itemPagedList: LiveData<PagedList<Item>>
    var liveDatyaSource: LiveData<PageKeyedDataSource<Int, Item>>


    init {
        val itemSourceFact = RepositoryDataSourchFactory()
        liveDatyaSource = itemSourceFact.itemLiveDS
        val config = (PagedList.Config.Builder()).setEnablePlaceholders(false)
            .setPageSize(15).build()
        itemPagedList = (LivePagedListBuilder(itemSourceFact, config)).build()
    }

}