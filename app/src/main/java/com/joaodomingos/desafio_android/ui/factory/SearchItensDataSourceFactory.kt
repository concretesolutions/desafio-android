package com.joaodomingos.desafio_android.ui.factory

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.joaodomingos.desafio_android.api.NetworkService
import com.joaodomingos.desafio_android.models.SearchItensModel
import com.joaodomingos.desafio_android.ui.data_source.ItemDataSource
import io.reactivex.disposables.CompositeDisposable

class SearchItensDataSourceFactory (
    private val compositeDisposable: CompositeDisposable,
    private val networkService: NetworkService
)
    : DataSource.Factory<Int, SearchItensModel>() {

    val newsDataSourceLiveData = MutableLiveData<ItemDataSource>()

    override fun create(): DataSource<Int, SearchItensModel> {
        val itemDataSource = ItemDataSource(networkService, compositeDisposable)
        newsDataSourceLiveData.postValue(itemDataSource)
        return itemDataSource
    }
}