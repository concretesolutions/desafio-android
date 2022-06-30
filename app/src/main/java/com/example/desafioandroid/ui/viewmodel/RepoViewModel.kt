package com.example.desafioandroid.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.desafioandroid.data.model.RepositoriesModel
import com.example.desafioandroid.domain.GetRepositoriesUserCase
import kotlinx.coroutines.launch

class RepoViewModel : ViewModel() {

    private val _repositoriesModel = MutableLiveData<List<RepositoriesModel>?>(null)
    val repositoriesModel: LiveData<List<RepositoriesModel>?> get() = _repositoriesModel

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> get() = _isLoading



    var getRepositories = GetRepositoriesUserCase()

    fun onCreate(){
        viewModelScope.launch{
            _isLoading.value = true
            val result: List<RepositoriesModel>? = getRepositories()
            if(!result.isNullOrEmpty()){
                _repositoriesModel.postValue(result)
                _isLoading.value = false
            }
        }
    }

    fun loadRepositories() {

       // _repositoriesModel.value = RepositoriesProvider.listRepositories()

    }
}