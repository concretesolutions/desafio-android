package com.joaoibarra.gitapp.api.paging

import android.arch.paging.DataSource
import com.joaoibarra.gitapp.api.GitApi
import com.joaoibarra.gitapp.api.model.Repo
import io.reactivex.disposables.CompositeDisposable

class RepositoryDatasourceFactory (
    private val compositeDisposable: CompositeDisposable,
    private val gitApi: GitApi
): DataSource.Factory<Int, Repo>() {

    override fun create(): DataSource<Int, Repo> {
        return RepositoryDataSource(gitApi, compositeDisposable)
    }
}
