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
    val repositoriesModel: LiveData<List<RepositoriesModel>?>
        get() = _repositoriesModel

    var getRepositories = GetRepositoriesUserCase()

    fun onCreate(){
        viewModelScope.launch{
            val result: List<RepositoriesModel>? = getRepositories()

            if(!result.isNullOrEmpty()){
                _repositoriesModel.postValue(result)
            }
        }
    }

    fun loadRepositories() {

       // _repositoriesModel.value = RepositoriesProvider.listRepositories()

    }
}