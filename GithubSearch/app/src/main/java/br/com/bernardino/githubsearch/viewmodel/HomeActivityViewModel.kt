package br.com.bernardino.githubsearch.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.bernardino.githubsearch.model.Repository
import br.com.bernardino.githubsearch.repository.ReposRepository

class HomeActivityViewModel : ViewModel() {
    var mRepositories = MutableLiveData<List<Repository>>()
    var isLoading = MutableLiveData<Boolean>()

    var apiError = MutableLiveData<Throwable>()

    fun getRepositories(): LiveData<List<Repository>> {
        isLoading.value = true
        ReposRepository.getRepositories(
            {
                mRepositories.value = it
                isLoading.value = false
            },
            {
                apiError.value = it
                isLoading.value = false
            }
        )
        return mRepositories
    }


}