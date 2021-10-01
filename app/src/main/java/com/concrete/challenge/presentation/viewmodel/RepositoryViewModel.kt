package com.concrete.challenge.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.concrete.challenge.domain.io.APIService
import com.concrete.challenge.domain.io.response.RepositoriesResponse
import com.concrete.challenge.utils.callService

class RepositoryViewModel(private val service: APIService) : ViewModel() {

    val repositoriesResponse: MutableLiveData<RepositoriesResponse> by lazy {
        MutableLiveData<RepositoriesResponse>()
    }

    private var page: Int = 1

    fun incrementPage() {
        page++
    }

    fun getRepositories() {
        callService(liveData = repositoriesResponse) { service.getRepositories(page) }
    }

}