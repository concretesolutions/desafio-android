package com.example.desafioandroid.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.desafioandroid.data.model.PullModel
import com.example.desafioandroid.data.model.RepoModel
import com.example.desafioandroid.data.model.SearchModel
import com.example.desafioandroid.domain.GetPullsByOwner
import com.example.desafioandroid.domain.GetRepos
import com.example.desafioandroid.domain.model.Repo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getRepos: GetRepos,
    private val getPullByOwner: GetPullsByOwner,

    ) : ViewModel() {

    private val _repositoriesModel = MutableLiveData<List<Repo>>(null)
    val repositoriesModel: LiveData<List<Repo>> get() = _repositoriesModel

    private val _repositoriesDb = MutableLiveData<List<Repo>>(null)
    val repositoriesDb: LiveData<List<Repo>> get() = _repositoriesDb

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _pullModel = MutableLiveData<List<PullModel>?>(null)
    val pullModel: LiveData<List<PullModel>?> get() = _pullModel

    var page = 1

    fun loadRepositories(query: String, page: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            val result: List<Repo> = getRepos(query,page)
            if (result != null) {
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

    fun searchRepos( newQuery : String ){
        page = 1
        loadRepositories(newQuery, page)
    }

}