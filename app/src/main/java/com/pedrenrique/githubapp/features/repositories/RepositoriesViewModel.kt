package com.pedrenrique.githubapp.features.repositories

import com.pedrenrique.githubapp.core.data.Repository
import com.pedrenrique.githubapp.core.domain.ListRepositories
import com.pedrenrique.githubapp.core.domain.LoadMoreRepositories
import com.pedrenrique.githubapp.features.common.PaginateViewModel
import com.pedrenrique.githubapp.features.common.adapter.model.RepositoryModelHolder

class RepositoriesViewModel(
    private val listRepositories: ListRepositories,
    private val loadMoreRepositories: LoadMoreRepositories
) : PaginateViewModel<Repository>() {

    fun load() {
        loadIfNeeded {
            listRepositories()
        }
    }

    fun loadMore() {
        loadMoreIfNeeded { p ->
            loadMoreRepositories(LoadMoreRepositories.Params(p))
        }
    }

    override fun transformData(data: Repository) =
        RepositoryModelHolder(data)
}

