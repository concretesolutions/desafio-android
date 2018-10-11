package com.diegoferreiracaetano.github.mock

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.diegoferreiracaetano.domain.repo.Repo


class RepoDataSource(val list:List<Repo>): PageKeyedDataSource<Int, Repo>() {

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int,Repo>) {
        callback.onResult(list,0,1)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int,Repo>) {
        callback.onResult(list,params.key+1)

    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int,Repo>) {

    }

    class RepoDataSourceFactory (val list: List<Repo>) : DataSource.Factory<Int, Repo>() {

        val source = MutableLiveData<RepoDataSource>()

        override fun create(): DataSource<Int, Repo> {
            val dataSource = RepoDataSource(list)
            source.postValue(dataSource)
            return dataSource
        }

    }
}