package com.abs.javarepos.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.abs.javarepos.model.Repo
import com.abs.javarepos.model.datasource.RepoDataSource
import com.abs.javarepos.model.repository.RepoRepository

class ReposViewModel : ViewModel() {

    private val repoRepository = RepoRepository()
    val repoPagedList: LiveData<PagedList<Repo>> = repoRepository.getRepos()
    val networkError: LiveData<Boolean> = repoRepository.networkError
    val repoDataSource: LiveData<RepoDataSource> = repoRepository.dataSource
}