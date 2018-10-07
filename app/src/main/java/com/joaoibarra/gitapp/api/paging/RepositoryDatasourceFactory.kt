package com.joaoibarra.gitapp.api.paging

import android.arch.paging.DataSource
import com.joaoibarra.gitapp.api.GitApi
import com.joaoibarra.gitapp.api.model.Item
import io.reactivex.disposables.CompositeDisposable

class RepositoryDatasourceFactory (
    private val compositeDisposable: CompositeDisposable,
    private val gitApi: GitApi
): DataSource.Factory<Int, Item>() {

    override fun create(): DataSource<Int, Item> {
        return RepositoryDataSource(gitApi, compositeDisposable)
    }
}