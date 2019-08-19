package com.joaodomingos.desafio_android.ui.view_models

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
    var newsList: LiveData<PagedList<SearchItensModel>>
    private val compositeDisposable = CompositeDisposable()
    private val pageSize = 5
    private val newsDataSourceFactory: SearchItensDataSourceFactory

    init {
        newsDataSourceFactory = SearchItensDataSourceFactory(compositeDisposable, networkService)
        val config = PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setInitialLoadSizeHint(pageSize * 2)
            .setEnablePlaceholders(false)
            .build()
        newsList = LivePagedListBuilder<Int, SearchItensModel>(newsDataSourceFactory, config).build()
    }


    fun getState(): LiveData<State> = Transformations.switchMap<ItemDataSource,
            State>(newsDataSourceFactory.newsDataSourceLiveData, ItemDataSource::state)

    fun retry() {
        newsDataSourceFactory.newsDataSourceLiveData.value?.retry()
    }

    fun listIsEmpty(): Boolean {
        return newsList.value?.isEmpty() ?: true
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}