package com.accenture.desafioandroid.data.datasource

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import android.arch.paging.PageKeyedDataSource
import com.accenture.desafioandroid.mvvm.model.PullRequest

class PullRequestDataSourchFactory : DataSource.Factory<Int, PullRequest>() {
    var itemLiveDS= MutableLiveData<PageKeyedDataSource<Int, PullRequest>>()


    override fun create(): DataSource<Int, PullRequest> {
        val itemdataSource= PullRequestDataSource()
        itemLiveDS.postValue(itemdataSource)
        return itemdataSource
    }


}