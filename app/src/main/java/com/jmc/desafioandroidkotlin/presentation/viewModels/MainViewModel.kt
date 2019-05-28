package com.jmc.desafioandroidkotlin.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.jmc.desafioandroidkotlin.presentation.model.RepositoryItem
import com.jmc.desafioandroidkotlin.presentation.ui.pagging.RepositoryBoundaryCallback

open class MainViewModel(
    boundaryCallback: RepositoryBoundaryCallback,
    val pagedRepositories: LiveData<PagedList<RepositoryItem>>
) : ViewModel() {
    val fetchRepositories = boundaryCallback.fetchLiveData
}