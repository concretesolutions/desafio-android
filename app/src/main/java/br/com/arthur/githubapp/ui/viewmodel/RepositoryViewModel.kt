package br.com.arthur.githubapp.ui.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.arthur.githubapp.data.repository.Repository
import br.com.arthur.githubapp.model.GitRepository
import br.com.arthur.githubapp.model.PullRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RepositoryViewModel(context: Context) : ViewModel() {

    private var repository: Repository = Repository(context)

    var currentPage = 0
        private set

    var mutableRepositoryList: MutableLiveData<List<GitRepository>> = MutableLiveData()
    var mutableErrorMessage: MutableLiveData<String> = MutableLiveData()
    var mutableLoading: MutableLiveData<Boolean> = MutableLiveData()

    var mutablePullRequestList: MutableLiveData<List<PullRequest>> = MutableLiveData()
    var mutableErrorPullMessage: MutableLiveData<String> = MutableLiveData()

    init {
        mutableRepositoryList.value = emptyList()
    }

    fun requestRepositories(page: Int) {
        mutableLoading.postValue(true)
        loadRepositories(page)
    }

    private fun loadRepositories(page: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            if (page > currentPage) {
                repository.getRepositories(page, { repositoryList ->
                    val list = mutableRepositoryList.value?.toMutableList()
                    list?.addAll(repositoryList)
                    if (list != null) {
                        mutableRepositoryList.postValue(list)
                    }
                    currentPage++
                }, { error ->
                    mutableErrorMessage.postValue(error)
                })
            }
        }
    }

    fun reset() {
        loadRepositories(1)
    }

    fun requestPulls(creator: String, repositoryName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getPulls(creator, repositoryName, { pulls ->
                mutablePullRequestList.postValue(pulls)
            }, { error ->
                mutableErrorPullMessage.postValue(error)
            })
        }
    }

}