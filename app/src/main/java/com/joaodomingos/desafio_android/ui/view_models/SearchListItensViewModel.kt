package com.joaodomingos.desafio_android.ui.view_models

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.joaodomingos.desafio_android.api.NetworkService
import com.joaodomingos.desafio_android.api.State
import com.joaodomingos.desafio_android.models.SearchItensModel
import com.joaodomingos.desafio_android.ui.data_source.ItemDataSource
import com.joaodomingos.desafio_android.ui.factory.SearchItensDataSourceFactory
import io.reactivex.disposables.CompositeDisposable

class SearchListItensViewModel: ViewModel() {

    private val networkService = NetworkService.getService()
    var itemList: LiveData<PagedList<SearchItensModel>>
    private val compositeDisposable = CompositeDisposable()
    private val pageSize = 5
    private val itemDataSourceFactory: SearchItensDataSourceFactory

    init {
        itemDataSourceFactory = SearchItensDataSourceFactory(compositeDisposable, networkService)
        val config = PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setInitialLoadSizeHint(pageSize * 2)
            .setEnablePlaceholders(false)
            .build()
        itemList = LivePagedListBuilder<Int, SearchItensModel>(itemDataSourceFactory, config).build()
    }


    fun getState(): LiveData<State> = Transformations.switchMap<ItemDataSource,
            State>(itemDataSourceFactory.itemDataSourceLiveData, ItemDataSource::state)

    fun retry() {
        itemDataSourceFactory.itemDataSourceLiveData.value?.retry()
    }

    fun listIsEmpty(): Boolean {
        return itemList.value?.isEmpty() ?: true
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}