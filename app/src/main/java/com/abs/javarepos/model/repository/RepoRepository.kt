package com.abs.javarepos.model.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.abs.javarepos.model.Repo
import com.abs.javarepos.model.datasource.RepoDataSource
import com.abs.javarepos.model.datasource.RepoDataSourceFactory

class RepoRepository {

    var networkError: LiveData<Boolean> = MutableLiveData()
    var dataSource: LiveData<RepoDataSource> = MutableLiveData()

    fun getRepos(): LiveData<PagedList<Repo>> {
        val factory = RepoDataSourceFactory()

        dataSource = factory.liveDataSource
        networkError = Transformations.switchMap(factory.liveDataSource) { it.networkError }

        val pagedListConfig = PagedList.Config.Builder()
            .setPageSize(30)
            .setEnablePlaceholders(false)
            .build()

        return LivePagedListBuilder(factory, pagedListConfig).build()
    }
}