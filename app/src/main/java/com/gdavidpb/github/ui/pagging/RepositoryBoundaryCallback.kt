package com.gdavidpb.github.ui.pagging

import androidx.paging.PagedList
import com.gdavidpb.github.domain.usecase.FetchRepositoriesUseCase
import com.gdavidpb.github.presentation.model.RepositoryItem
import com.gdavidpb.github.utils.LiveCompletable

open class RepositoryBoundaryCallback(
    val fetchLiveData: LiveCompletable,
    private val fetchRepositoriesUseCase: FetchRepositoriesUseCase
) : PagedList.BoundaryCallback<RepositoryItem>() {
    override fun onItemAtEndLoaded(itemAtEnd: RepositoryItem) = fetchRepositories()

    override fun onZeroItemsLoaded() = fetchRepositories()

    private fun fetchRepositories() = fetchRepositoriesUseCase.execute(liveData = fetchLiveData, params = Unit)
}