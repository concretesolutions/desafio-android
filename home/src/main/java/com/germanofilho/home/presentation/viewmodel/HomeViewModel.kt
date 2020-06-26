package com.germanofilho.home.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.germanofilho.core.helper.Resource
import com.germanofilho.core.viewmodel.BaseViewModel
import com.germanofilho.home.repository.HomeRepository
import com.germanofilho.network.feature.repositorylist.model.entity.GitRepositoryResponse
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: HomeRepository) : BaseViewModel() {

    val repositoryList = MutableLiveData<Resource<GitRepositoryResponse>>()

    fun getRepositoryList(page: Int = 1) {
        viewModelScope.launch {
            repositoryList.loading(true)
            try {
                repositoryList.success(repository.getRepositoryList(page))
            } catch (e: Exception) {
                repositoryList.error(e)
            } finally {
                repositoryList.loading(false)
            }
        }
    }
}