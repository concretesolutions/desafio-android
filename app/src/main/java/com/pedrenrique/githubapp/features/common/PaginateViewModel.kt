package com.pedrenrique.githubapp.features.common

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pedrenrique.githubapp.core.data.PaginatedData
import com.pedrenrique.githubapp.core.exceptions.EmptyResultException
import com.pedrenrique.githubapp.core.exceptions.NoMoreResultException
import com.pedrenrique.githubapp.core.platform.ModelHolder
import kotlinx.coroutines.launch

abstract class PaginateViewModel<T> : ViewModel() {
    var page = 0
        private set
    val state = MutableLiveData<DataState>()

    abstract fun transformData(data: T): ModelHolder

    protected fun loadIfNeeded(requestData: suspend () -> PaginatedData<T>) {
        val value = state.value
        if (value == null || value is DataState.Failed) {
            state.value = DataState.Pending
            retrieveData {
                requestData()
            }
        }
    }

    protected fun loadMoreIfNeeded(requestData: suspend (page: Int) -> PaginatedData<T>) {
        val value = state.value
        val data = (value as? DataState.Loaded)?.data ?: (value as? DataState.NextFailed)?.original
        if (data != null) {
            state.value = DataState.NextPending(data)
            retrieveData(data) {
                requestData(page)
            }
        }
    }

    private fun retrieveData(
        lastData: List<ModelHolder>? = null,
        provider: suspend () -> PaginatedData<T>
    ) {
        viewModelScope.launch {
            state.value = try {
                onDataReceived(lastData, provider())
            } catch (e: EmptyResultException) {
                DataState.Empty
            } catch (e: NoMoreResultException) {
                DataState.Completed(lastData ?: listOf())
            } catch (e: Throwable) {
                lastData?.let { DataState.NextFailed(e, it) }
                    ?: DataState.Failed(e)
            }
        }
    }

    private fun onDataReceived(
        lastData: List<ModelHolder>?,
        result: PaginatedData<T>
    ): DataState.Loaded {
        val data = (lastData ?: listOf()).toMutableList()
        data.addAll(result.content.map {
            transformData(it)
        })
        page = result.page
        return DataState.Loaded(data)
    }
}