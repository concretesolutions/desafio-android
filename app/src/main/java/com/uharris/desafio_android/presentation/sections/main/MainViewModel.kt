package com.uharris.desafio_android.presentation.sections.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.uharris.desafio_android.data.base.Failure
import com.uharris.desafio_android.data.base.Result
import com.uharris.desafio_android.domain.models.Repository
import com.uharris.desafio_android.domain.usecases.actions.FetchRepositories
import com.uharris.desafio_android.presentation.state.Resource
import com.uharris.desafio_android.presentation.state.ResourceState
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val fetchRepositories: FetchRepositories,
    application: Application
): AndroidViewModel(application) {

    val repositoriesLiveData: MutableLiveData<Resource<List<Repository>>> = MutableLiveData()

    fun fetchRepositories(page: Int) {
        repositoriesLiveData.postValue(Resource(ResourceState.LOADING, null, null))
        fetchRepositories(FetchRepositories.Params(page)){
            when(it){
                is Result.Success -> handleRepositories(it.data.items)
                is Result.Error -> handleError(it.failure)
            }
        }
    }

    private fun handleRepositories(repositories: List<Repository>) {

        repositoriesLiveData.postValue(Resource(ResourceState.SUCCESS, repositories, null))
    }

    private fun handleError(failure: Failure) {
        val message = when (failure) {
            is Failure.NetworkConnection -> "Error with network connection"
            else -> "Error with the server."
        }

        repositoriesLiveData.postValue(Resource(ResourceState.ERROR, null, message))
    }
}