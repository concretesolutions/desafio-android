package br.com.concrete.githubconcretechallenge.features.repositories.datasource

import androidx.paging.DataSource
import br.com.concrete.githubconcretechallenge.features.repositories.model.RepositoryModel
import io.reactivex.disposables.CompositeDisposable

class RepositoriesListDataSourceFactory(private val remoteDataSource: RepositoriesListRemoteDataSource)
    : DataSource.Factory<Int, RepositoryModel>() {

    val compositeDisposable = CompositeDisposable()

    var invalidateCache = false

    override fun create(): DataSource<Int, RepositoryModel> {
        return RepositoriesListPagedDataSource(remoteDataSource, compositeDisposable, invalidateCache)
    }

}