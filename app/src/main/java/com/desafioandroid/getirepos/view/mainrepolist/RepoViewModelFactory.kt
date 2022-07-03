package com.desafioandroid.getirepos.view.mainrepolist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.desafioandroid.getirepos.data.GetReposDatasource
import com.desafioandroid.getirepos.data.GetReposRepository

class RepoViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val getReposDatasource = GetReposDatasource()
        val getReposRepository = GetReposRepository(getReposDatasource)
        return RepoViewModel(getReposRepository) as T
    }
}