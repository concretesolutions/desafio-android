package br.com.henriqueoliveira.desafioandroidconcrete.viewmodel

import br.com.henriqueoliveira.desafioandroidconcrete.service.models.PullRequest
import br.com.henriqueoliveira.desafioandroidconcrete.service.models.Repository
import br.com.henriqueoliveira.desafioandroidconcrete.service.models.ServerResponse
import br.com.henriqueoliveira.desafioandroidconcrete.service.repository.RepositoryDataSource
import br.com.henriqueoliveira.desafioandroidconcrete.service.repository.Resource
import br.com.henriqueoliveira.desafioandroidconcrete.service.repository.ResultListener


class RepositoryListViewModel(val repository: RepositoryDataSource) : BaseViewModel<List<Repository>, List<PullRequest>>() {

    var repositoriesLiveData: MutableList<Repository> = arrayListOf()
    var pullRequestLiveData: MutableList<PullRequest> = arrayListOf()
    var page = 0
    var lastPageReached = false


    fun loadPullRequests(owner: String, repositoryName: String) {
        isLoading.postValue(true)
        repository.getPullRequests(owner, repositoryName, object : ResultListener<List<PullRequest>> {
            override fun onSuccess(data: List<PullRequest>?) {
                isLoading.postValue(false)
                data?.let {
                    if (it.isEmpty()) {
                        _statePR.postValue(Resource.error("No Pull Requests to show", pullRequestLiveData))
                        return
                    }

                    pullRequestLiveData.addAll(it)
                    _statePR.postValue(Resource.success(pullRequestLiveData))
                }


            }

            override fun onFailure(message: String) {
                isLoading.postValue(false)
                _statePR.postValue(Resource.error(message))
            }

        })
    }

    fun loadRepositories() {
        isLoading.postValue(true)
        repository.getRepositories(page, object : ResultListener<ServerResponse> {
            override fun onSuccess(data: ServerResponse?) {

                isLoading.postValue(false)
                data?.items?.let {
                    if (it.isEmpty()) {
                        lastPageReached = true
                        _state.postValue(Resource.error("No more repositories to load", repositoriesLiveData))
                        return
                    }

                    repositoriesLiveData.addAll(it)
                    _state.postValue(Resource.success(repositoriesLiveData))

                }


            }

            override fun onFailure(message: String) {
                isLoading.postValue(false)
                _state.postValue(Resource.error(message))
            }

        })
    }

    fun refreshList() {
        page = 1
        loadRepositories()

    }


    fun loadNextPage() {
        if (!isLoading.value!! && !lastPageReached) {
            page++
            isLoading.postValue(true)
            loadRepositories()
        }
    }

}
