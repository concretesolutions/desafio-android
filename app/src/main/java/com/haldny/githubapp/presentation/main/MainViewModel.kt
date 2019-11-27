package com.haldny.githubapp.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haldny.githubapp.common.Resource
import com.haldny.githubapp.domain.model.Repository
import com.haldny.githubapp.domain.repository.IGithubRepository
import kotlinx.coroutines.launch

class MainViewModel(private val githubRepository: IGithubRepository): ViewModel() {

    private val repositoryLiveData = MutableLiveData<Resource<MutableList<Repository>>>()
    private val items = mutableListOf<Repository>()
    var currentPage = 1
    var isLoading = true
    var isLastPage = false

    init {
        fetchListItem()
    }

    fun getItems(): LiveData<Resource<MutableList<Repository>>> = repositoryLiveData

    private fun fetchListItem(page: Int = 1) {
        repositoryLiveData.loading()

        viewModelScope.launch {
            try {
                githubRepository.getRepositories(page)?.let {
                    items.addAll(it)
                    if (items.isNotEmpty()) {
                        repositoryLiveData.success(items)
                    }
                }
            } catch (t: Throwable){
                repositoryLiveData.error(t)
            }
        }
    }

    fun nextPage() {
        fetchListItem(++currentPage)
        isLoading = false
    }

    fun backPreviousPage() {
        fetchListItem(currentPage)
    }

    fun refreshViewModel() {
        currentPage = 1
        isLastPage = false
        fetchListItem()
    }

    fun paginationFinished(){
        isLoading = true
        isLastPage = true
    }

    private fun <T> MutableLiveData<Resource<T>>.success(data: T?) {
        value = Resource.success(data)
    }

    private fun <T> MutableLiveData<Resource<T>>.error(t: Throwable?) {
        value = Resource.error(t)
    }

    private fun <T> MutableLiveData<Resource<T>>.loading() {
        value = Resource.loading()
    }
}