package br.com.concrete.githubconcretechallenge.features.repositories.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import br.com.concrete.githubconcretechallenge.Constants
import br.com.concrete.githubconcretechallenge.features.repositories.datasource.RepositoriesListDataSource
import br.com.concrete.githubconcretechallenge.features.repositories.datasource.RepositoriesListDataSourceFactory
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
        val repositoriesListDataSource = pagedList.value?.dataSource as RepositoriesListDataSource
        repositoriesListDataSource.invalidate()
    }

    override fun onCleared() {
        dataSourceFactory.compositeDisposable.dispose()
    }


}
