package com.jsouza.repocatalog.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jsouza.repocatalog.data.repocatalog.remote.response.Repository
import com.jsouza.repocatalog.domain.usecase.FetchRepositoriesFromApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RepositoryCatalogViewModel(
    private val fetchRepositoriesFromApi: FetchRepositoriesFromApi
) : ViewModel() {

    private val coroutineScope = Dispatchers.IO
    private val _repoList = MutableLiveData<List<Repository>>()
    val repoList: LiveData<List<Repository>> = _repoList

    init {
        viewModelScope.launch(context = coroutineScope) {
            try {

                _repoList.postValue(fetchRepositoriesFromApi(1)?.items)
            } catch (e: Exception) {
                Log.i("Error api", e.message.toString())
            }
        }
    }
}
