package com.abs.javarepos.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.abs.javarepos.model.Repo
import com.abs.javarepos.model.repository.RepoRepository

class ReposViewModel : ViewModel() {

    private val repoRepository = RepoRepository()
    var repoPagedList: LiveData<PagedList<Repo>> = repoRepository.getRepos()
}