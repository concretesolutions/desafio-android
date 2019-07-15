package com.pedrenrique.githubapp.features.pr

import com.pedrenrique.githubapp.core.data.PullRequest
import com.pedrenrique.githubapp.core.data.Repository
import com.pedrenrique.githubapp.core.domain.ListPRFromRepository
import com.pedrenrique.githubapp.core.domain.LoadMorePRFromRepository
import com.pedrenrique.githubapp.features.common.PaginateViewModel
import com.pedrenrique.githubapp.features.common.adapter.model.PullRequestModelHolder

class PullRequestsViewModel(
    private val listPRFromRepository: ListPRFromRepository,
    private val loadMorePRFromRepository: LoadMorePRFromRepository
) : PaginateViewModel<PullRequest>() {
    fun load(repository: Repository) {
        loadIfNeeded {
            listPRFromRepository(ListPRFromRepository.Params(repository))
        }
    }

    fun loadMore(repository: Repository) {
        loadMoreIfNeeded { p ->
            loadMorePRFromRepository(LoadMorePRFromRepository.Params(p, repository))
        }
    }

    override fun transformData(data: PullRequest) =
        PullRequestModelHolder(data)
}