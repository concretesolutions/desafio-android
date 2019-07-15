package com.pedrenrique.githubapp.features.repositories

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pedrenrique.githubapp.core.data.PaginatedData
import com.pedrenrique.githubapp.core.data.Repository
import com.pedrenrique.githubapp.core.domain.ListRepositories
import com.pedrenrique.githubapp.core.domain.LoadMoreRepositories
import com.pedrenrique.githubapp.core.exceptions.EmptyResultException
import com.pedrenrique.githubapp.core.exceptions.NoMoreResultException
import com.pedrenrique.githubapp.core.platform.ModelHolder
import com.pedrenrique.githubapp.features.common.model.*
import kotlinx.coroutines.launch

class RepositoriesViewModel(
    private val listRepositories: ListRepositories,
    private val loadMoreRepositories: LoadMoreRepositories
) : ViewModel() {
    private var page = 0

    val state = MutableLiveData<RepositoriesState>()

    fun load() {
        val value = state.value
        if (value == null || value is RepositoriesState.Failed) {
            state.value = RepositoriesState.Pending
            retrieveData {
                listRepositories()
            }
        }
    }

    fun loadMore() {
        val value = state.value
        val data = (value as? RepositoriesState.Loaded)?.data ?: (value as? RepositoriesState.NextFailed)?.original
        if (data != null) {
            state.value = RepositoriesState.NextPending(data)
            retrieveData(data) {
                loadMoreRepositories(LoadMoreRepositories.Params(page))
            }
        }
    }

    private fun retrieveData(lastData: List<ModelHolder>? = null, provider: suspend () -> PaginatedData<Repository>) {
        viewModelScope.launch {
            state.value = try {
                onRepositoriesReceived(lastData, provider())
            } catch (e: EmptyResultException) {
                RepositoriesState.Empty
            } catch (e: NoMoreResultException) {
                RepositoriesState.Completed(lastData ?: listOf())
            } catch (e: Throwable) {
                lastData?.let { RepositoriesState.NextFailed(e, it) }
                    ?: RepositoriesState.Failed(e)
            }
        }
    }

    private fun onRepositoriesReceived(
        lastData: List<ModelHolder>?,
        result: PaginatedData<Repository>
    ): RepositoriesState.Loaded {
        val data = (lastData ?: listOf()).toMutableList()
        data.addAll(result.content.map {
            RepositoryModelHolder(it)
        })
        page = result.page
        return RepositoriesState.Loaded(data)
    }
}

