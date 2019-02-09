package com.accenture.desafioandroid.mvvm.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PageKeyedDataSource
import android.arch.paging.PagedList
import com.accenture.desafioandroid.data.datasource.PullRequestDataSourchFactory
import com.accenture.desafioandroid.mvvm.model.PullRequest

class PullRequestViewModel : ViewModel() {
    var itemPagedList: LiveData<PagedList<PullRequest>>
    var liveDatyaSource: LiveData<PageKeyedDataSource<Int, PullRequest>>

    init {

        val itemSourceFact = PullRequestDataSourchFactory()
        liveDatyaSource = itemSourceFact.itemLiveDS
        val config = (PagedList.Config.Builder()).setEnablePlaceholders(false)
            .setPageSize(15).build()
        itemPagedList = (LivePagedListBuilder(itemSourceFact, config)).build()
    }
}