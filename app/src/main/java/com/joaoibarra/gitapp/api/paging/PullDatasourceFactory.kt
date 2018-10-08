package com.joaoibarra.gitapp.api.paging

import android.arch.paging.DataSource
import com.joaoibarra.gitapp.api.GitApi
import com.joaoibarra.gitapp.api.model.Pull
import com.joaoibarra.gitapp.api.model.Repo
import io.reactivex.disposables.CompositeDisposable

class PullDatasourceFactory (
    private val compositeDisposable: CompositeDisposable,
    private val gitApi: GitApi,
    private val user: String,
    private val repo: String
): DataSource.Factory<Int, Pull>() {

    override fun create(): DataSource<Int, Pull> {
        return PullDataSource(gitApi, compositeDisposable, user, repo)
    }
}