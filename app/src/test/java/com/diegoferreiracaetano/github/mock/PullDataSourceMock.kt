package com.diegoferreiracaetano.github.mock

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.diegoferreiracaetano.domain.pull.Pull


class PullDataSource(val list:List<Pull>): PageKeyedDataSource<Int, Pull>() {

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int,Pull>) {
        callback.onResult(list,0,1)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int,Pull>) {
        callback.onResult(list,params.key+1)

    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int,Pull>) {

    }

    class PullDataSourceFactory (val list: List<Pull>) : DataSource.Factory<Int, Pull>() {

        val source = MutableLiveData<PullDataSource>()

        override fun create(): DataSource<Int, Pull> {
            val dataSource = PullDataSource(list)
            source.postValue(dataSource)
            return dataSource
        }

    }
}