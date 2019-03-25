package dev.renatoneto.githubrepos.ui.repositorylist

import androidx.lifecycle.MutableLiveData
import dev.renatoneto.githubrepos.R
import dev.renatoneto.githubrepos.base.BaseViewModel
import dev.renatoneto.githubrepos.model.github.GithubRepository
import dev.renatoneto.githubrepos.network.github.GithubDataSource
import dev.renatoneto.githubrepos.util.rx.SchedulerContract

class RepositoryListViewModel(private val repository: GithubDataSource,
                              private val schedulers: SchedulerContract) : BaseViewModel() {

    var currentPage = 1

    var lastPageLoaded = 0

    val repositoriesList = ArrayList<GithubRepository>()

    val repositories = MutableLiveData<ArrayList<GithubRepository>>()

    var erro: Int = 0

    fun loadRepositories() {

        if (currentPage != lastPageLoaded) {

            loading.postValue(true)
            error.postValue(null)

            val disposable = repository.getRepositoriesList(currentPage)
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.ui())
                .subscribe({

                    loading.postValue(false)

                    repositoriesList.addAll(it.items)
                    repositories.postValue(repositoriesList)

                    lastPageLoaded = currentPage

                }, {
                    loading.postValue(false)
                    showError(it)
                    erro = R.string.error_connection
                })

            disposables.add(disposable)

        }

    }

    fun goToNextPage() {
        currentPage += 1
        loadRepositories()
    }

}
