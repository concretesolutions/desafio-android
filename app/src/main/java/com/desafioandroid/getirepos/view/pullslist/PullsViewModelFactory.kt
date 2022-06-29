package com.desafioandroid.getirepos.view.pullslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.desafioandroid.getirepos.data.GetReposDatasource
import com.desafioandroid.getirepos.data.GetReposRepository

class PullsViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val getReposDatasource = GetReposDatasource()
        val getReposRepository = GetReposRepository(getReposDatasource)
        return PullsViewModel(getReposRepository) as T
    }
}