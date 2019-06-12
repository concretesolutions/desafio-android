package com.abs.javarepos.model.repository

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.abs.javarepos.model.Repo
import com.abs.javarepos.model.datasource.RepoDataSourceFactory

class RepoRepository {

    fun getRepos(): LiveData<PagedList<Repo>> {
        val factory = RepoDataSourceFactory()
        return LivePagedListBuilder(factory, 30).build()
    }
}