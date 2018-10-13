package com.rafaelpereiraramos.desafioAndroid.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.rafaelpereiraramos.desafioAndroid.database.model.RepoTO
import com.rafaelpereiraramos.desafioAndroid.repository.RepoRepository
import javax.inject.Inject

/**
 * Created by Rafael P. Ramos on 13/10/2018.
 */
class RepoViewModel @Inject constructor(private val repoRepository: RepoRepository) : ViewModel() {

    companion object {
        private const val LOCAL_SNAPSHOT_SIZE = 10
    }

    private val queryLiveData = MutableLiveData<String>()

    val repos: LiveData<PagedList<RepoTO>> =
            Transformations.map(queryLiveData) {
                var result = repoRepository.search(it)
                return@map LivePagedListBuilder(result, LOCAL_SNAPSHOT_SIZE)
                        .setBoundaryCallback(repoRepository)
                        .build().value
            }

    val networkError: LiveData<String> =
            Transformations.map(repoRepository.networkErrors) {input -> input}

    fun searchRepo(queryString: String) {
        queryLiveData.postValue(queryString)
    }
}