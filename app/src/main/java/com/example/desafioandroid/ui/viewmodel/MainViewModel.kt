package com.example.desafioandroid.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.desafioandroid.data.model.PullModel
import com.example.desafioandroid.data.network.ApiResponseStatus
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

    private val _status = MutableLiveData<ApiResponseStatus>(null)
    val status: LiveData<ApiResponseStatus> get() = _status

    private val _pullModel = MutableLiveData<List<PullModel>?>(null)
    val pullModel: LiveData<List<PullModel>?> get() = _pullModel

    private var page = 1

    fun loadRepositories(query: String, page: Int) {
        viewModelScope.launch {
            try {
                _status.value = ApiResponseStatus.LOADING
                val result: List<Repo> = getRepos(query, page)
                if (result.isNotEmpty()) {
                    _repositoriesModel.postValue(result)
                }
                _status.value = ApiResponseStatus.SUCCESS
            } catch (e: Exception) {
                _status.value = ApiResponseStatus.ERROR
            }
        }
    }

    fun loadPullOwner(owner: String, repo: String) {
        viewModelScope.launch {
            try {
                _status.value = ApiResponseStatus.LOADING
                val result: List<PullModel>? = getPullByOwner(owner, repo)
                if (!result.isNullOrEmpty()) {
                    //     Log.i("onCreatePullOwner", result.toString())
                    _pullModel.postValue(result)
                    _status.value = ApiResponseStatus.SUCCESS
                } else {
                    _status.value = ApiResponseStatus.ERROR
                }
            } catch (e: Exception) {
                _status.value = ApiResponseStatus.ERROR
            }
        }
    }

    /*fun searchRepos(newQuery: String) {
        page = 1
        loadRepositories(newQuery, page)
    }*/

}