package com.abs.javarepos.model.datasource

import androidx.paging.DataSource
import com.abs.javarepos.model.Repo

class RepoDataSourceFactory : DataSource.Factory<Int, Repo>() {

    override fun create(): DataSource<Int, Repo> {
        return RepoDataSource()
    }
}