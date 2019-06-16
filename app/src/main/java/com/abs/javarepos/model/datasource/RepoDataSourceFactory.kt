package com.abs.javarepos.model.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.abs.javarepos.model.Repo

class RepoDataSourceFactory : DataSource.Factory<Int, Repo>() {

    var liveDataSource = MutableLiveData<RepoDataSource>()

    override fun create(): DataSource<Int, Repo> {
        val dataSource = RepoDataSource()
        liveDataSource.postValue(dataSource)
        return dataSource
    }
}