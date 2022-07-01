package com.example.desafioandroid.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.desafioandroid.data.model.PullModel
import com.example.desafioandroid.data.model.RepoModel
import com.example.desafioandroid.data.model.RepositoriesModel
import com.example.desafioandroid.domain.GetPullsByOwner
import com.example.desafioandroid.domain.GetRepoByOwner
import com.example.desafioandroid.domain.GetRepositories
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getRepositories: GetRepositories,
    private val getPullByOwner: GetPullsByOwner,
    private val getRepoByOwner: GetRepoByOwner,

    ) : ViewModel() {

    private val _repositoriesModel = MutableLiveData<List<RepositoriesModel>?>(null)
    val repositoriesModel: LiveData<List<RepositoriesModel>?> get() = _repositoriesModel

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _pullModel = MutableLiveData<List<PullModel>?>(null)
    val pullModel: LiveData<List<PullModel>?> get() = _pullModel

    private val _repoModel = MutableLiveData<RepoModel?>()
    val repoModel: LiveData<RepoModel?> get() = _repoModel

    fun loadRepositories() {
        viewModelScope.launch {
            _isLoading.value = true
            val result: List<RepositoriesModel>? = getRepositories()
            if (!result.isNullOrEmpty()) {
                _repositoriesModel.postValue(result)
                _isLoading.value = false
            }
        }
    }

    fun loadPullOwner(owner: String, repo: String) {
        viewModelScope.launch {
            _isLoading.value = true
            val result: List<PullModel>? = getPullByOwner(owner, repo)
            Log.i("onCreatePullOwner", result.toString())
            if (!result.isNullOrEmpty()) {
                _pullModel.postValue(result)
                _isLoading.value = false
            }
        }
    }

    fun onRepoOwner(owner: String, repo: String) {
        viewModelScope.launch {
            _isLoading.value = true
            val result: RepoModel? = getRepoByOwner(owner, repo)
            Log.i("onRepoOwner", result.toString())
            if (result != null) {
                _repoModel.postValue(result)
                _isLoading.value = false
            }
        }
    }
}