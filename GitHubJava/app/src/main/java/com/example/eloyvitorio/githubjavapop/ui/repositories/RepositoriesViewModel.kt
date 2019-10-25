package com.example.eloyvitorio.githubjavapop.ui.repositories

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.eloyvitorio.githubjavapop.data.model.Repository
import com.example.eloyvitorio.githubjavapop.data.network.CallBackRepo
import com.example.eloyvitorio.githubjavapop.data.network.RepositoriesAPI

class RepositoriesViewModel(private val repositoriesAPI: RepositoriesAPI) : ViewModel() {
    var error = MutableLiveData<String>()
    var repositoryList = MutableLiveData<List<Repository>>()
    private var mCurrentPage: Int = 1

    fun fetchRepositories() {
        repositoriesAPI.getJavaRepositories(mCurrentPage++, object : CallBackRepo {
            override fun onSucessGetRepository(repositories: List<Repository>) {
                this@RepositoriesViewModel.repositoryList.postValue(repositories)
            }

            override fun onErrorGetRepository(message: String) {
                this@RepositoriesViewModel.error.postValue(message)
            }
        })
    }
}