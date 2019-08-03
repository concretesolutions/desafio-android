package br.com.concrete.githubconcretechallenge.features.repositories.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import br.com.concrete.githubconcretechallenge.Constants
import br.com.concrete.githubconcretechallenge.features.repositories.datasource.RepositoriesListDataSourceFactory
import br.com.concrete.githubconcretechallenge.features.repositories.datasource.RepositoriesListPagedDataSource
import br.com.concrete.githubconcretechallenge.features.repositories.model.RepositoryModel

class RepositoriesListViewModel(private val dataSourceFactory: RepositoriesListDataSourceFactory) : ViewModel() {

    private val pagedListConfig = PagedList.Config.Builder()
        .setInitialLoadSizeHint(Constants.ITEMS_PER_PAGE)
        .setPageSize(Constants.ITEMS_PER_PAGE)
        .build()

    private val pagedList = LivePagedListBuilder(dataSourceFactory, pagedListConfig)
        .setInitialLoadKey(1)
        .build()

    fun getRepositories(): LiveData<PagedList<RepositoryModel>> {
        return pagedList
    }

    fun refreshData() {
        dataSourceFactory.invalidateCache = true
        val repositoriesListDataSource = pagedList.value?.dataSource as RepositoriesListPagedDataSource
        repositoriesListDataSource.invalidate()
        repositoriesListDataSource.addInvalidatedCallback {
            dataSourceFactory.invalidateCache = false
        }
    }

    override fun onCleared() {
        dataSourceFactory.compositeDisposable.dispose()
    }


}
