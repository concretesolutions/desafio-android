package com.dobler.desafio_android.ui.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.dobler.desafio_android.data.model.GithubRepository
import com.dobler.desafio_android.data.repository.githubRepository.Repository
import com.dobler.desafio_android.util.paging.Listing
import com.dobler.desafio_android.util.rx.SchedulerContract

class ListRepositoryViewModel(
    val repository: Repository,
    private val schedulers: SchedulerContract
) : ViewModel() {

    private var started: Boolean = false
    private var repoResult = MutableLiveData<Listing<GithubRepository>>()

    val githubRepositories = Transformations.switchMap(repoResult, { it.pagedList })!!
    val networkState = Transformations.switchMap(repoResult, { it.networkState })!!
    val refreshState = Transformations.switchMap(repoResult, { it.refreshState })!!

    fun refresh() {
        repoResult.value?.refresh?.invoke()
    }

    fun retry() {
        val listing = repoResult?.value
        listing?.retry?.invoke()
    }


    fun loadList() {

        if (!started) {
            repository.getPage()
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.ui())
                .subscribe({
                    repoResult.postValue(it)
                    started = true

                }, {
                })

        }
    }


}
