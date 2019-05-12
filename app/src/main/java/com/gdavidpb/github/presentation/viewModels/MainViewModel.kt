package com.gdavidpb.github.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.gdavidpb.github.presentation.model.RepositoryItem
import com.gdavidpb.github.ui.pagging.RepositoryBoundaryCallback

open class MainViewModel(
    boundaryCallback: RepositoryBoundaryCallback,
    val pagedRepositories: LiveData<PagedList<RepositoryItem>>
) : ViewModel() {
    val fetchRepositories = boundaryCallback.fetchLiveData
}