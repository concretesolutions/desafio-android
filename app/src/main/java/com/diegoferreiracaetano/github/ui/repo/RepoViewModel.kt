package com.diegoferreiracaetano.github.ui.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.diegoferreiracaetano.domain.Constants
import com.diegoferreiracaetano.domain.repo.Repo
import com.diegoferreiracaetano.domain.repo.interactor.CallbackRepoInteractor
import com.diegoferreiracaetano.domain.repo.interactor.GetListRepoInteractor

class RepoViewModel(private val getRepoInteractor: GetListRepoInteractor,
                    private val callback: CallbackRepoInteractor) : ViewModel() {

    val result : LiveData<PagedList<Repo>>

    init {

        val config = PagedList.Config.Builder()
                .setPageSize(Constants.PAGE_SIZE)
                .setInitialLoadSizeHint(Constants.PAGE_SIZE)
                .setEnablePlaceholders(true)
                .build()

        result = LivePagedListBuilder<Int, Repo>(getRepoInteractor.execute(), config)
                .setBoundaryCallback(callback)
                .build()
    }

    fun retry() {
        callback.retry()
    }


    fun refresh() {
        callback.onZeroItemsLoaded()
    }

    val networkState = Transformations.switchMap(result,{callback.networkState})
    val initialLoad =  Transformations.switchMap(result,{callback.initialLoad})

    override fun onCleared() {
        super.onCleared()
        callback.clear()
    }


}
