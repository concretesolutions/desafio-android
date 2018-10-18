package com.example.consultor.testacc.mvvm.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PageKeyedDataSource
import android.arch.paging.PagedList
import com.example.consultor.testacc.data.pojos.Repository
import com.example.consultor.testacc.presentation.datasources.SimpleDataSourceFactory


class RepositoryListViewModel : ViewModel() {


     var itemPagedList: LiveData<PagedList<Repository>>
     var liveDatyaSource: LiveData<PageKeyedDataSource<Int, Repository>>

     //configuramos nuestro source para que cargue la cantidad inidicada
    init {
        val itemSourceFact = SimpleDataSourceFactory()
        liveDatyaSource = itemSourceFact.itemLiveDS
        val config = (PagedList.Config.Builder()).setEnablePlaceholders(false)
            .setPageSize(20).build()
        itemPagedList = (LivePagedListBuilder(itemSourceFact, config)).build()
    }



}
