package br.com.concrete.desafio.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import br.com.concrete.sdk.RepoRepository
import br.com.concrete.sdk.model.Page
import br.com.concrete.sdk.model.Repo
import timber.log.Timber

class RepoListViewModel : ViewModel() {

    private val repo = RepoRepository

    private val data = MutableLiveData<Page<Repo>>()

    fun searchRepos(page: Int): LiveData<Page<Repo>> {
        requestFromApi(page)
        return data
    }

    private fun requestFromApi(page: Int) {
        repo.search(page).subscribe(
                { data.postValue(it) },
                { Timber.e(it) }
        )
    }

}