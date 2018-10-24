package br.com.henriqueoliveira.desafioandroidconcrete.viewmodel

import br.com.henriqueoliveira.desafioandroidconcrete.service.models.Repository
import br.com.henriqueoliveira.desafioandroidconcrete.service.models.ServerResponse
import br.com.henriqueoliveira.desafioandroidconcrete.service.repository.RepositoryDataSource
import br.com.henriqueoliveira.desafioandroidconcrete.service.repository.Resource
import br.com.henriqueoliveira.desafioandroidconcrete.service.repository.ResultListener


class RepositoryListViewModel(val repository: RepositoryDataSource) : BaseViewModel<List<Repository>>() {

    var repositoriesLiveData: MutableList<Repository> = arrayListOf()
    var page = 0
    var lastPageReached = false


    fun loadRepositories() {
        repository.getRepositories(page, object : ResultListener<ServerResponse> {
            override fun onSuccess(data: ServerResponse?) {

                data?.items?.let {
                    if(it.isEmpty()){
                        isLoading.postValue(false)
                        lastPageReached = true
                        _state.postValue(Resource.error("No more repositories to load", repositoriesLiveData))
                        return
                    }

                    repositoriesLiveData.addAll(it)
                    _state.postValue(Resource.success(repositoriesLiveData))
                    isLoading.postValue(false)
                }


            }

            override fun onFailure(message: String) {
                isLoading.postValue(false)
                _state.postValue(Resource.error(message))
            }

        })
    }

    fun loadNextPage() {
        if (!isLoading.value!! && !lastPageReached) {
            page++
            isLoading.postValue(true)
            loadRepositories()
        }
    }

//    fun getNetworkState(): LiveData<NetworkState> {
//        return repository.getNetworkState()
//    }

}
