package com.gdavidpb.github.ui.pagging

import androidx.paging.PagedList
import com.gdavidpb.github.domain.usecase.FetchRepositoriesUseCase
import com.gdavidpb.github.presentation.model.RepositoryItem
import com.gdavidpb.github.utils.LiveCompletable

open class RepositoryBoundaryCallback(
    val fetchLiveData: LiveCompletable,
    private val fetchRepositoriesUseCase: FetchRepositoriesUseCase
) : PagedList.BoundaryCallback<RepositoryItem>() {
    override fun onItemAtEndLoaded(itemAtEnd: RepositoryItem) = fetchRepositories(itemAtEnd.page + 1)

    override fun onZeroItemsLoaded() = fetchRepositories(page = 1)

    private fun fetchRepositories(page: Int) = fetchRepositoriesUseCase.execute(liveData = fetchLiveData, params = page)
}