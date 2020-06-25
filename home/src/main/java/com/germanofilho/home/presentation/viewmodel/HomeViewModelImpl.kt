package com.germanofilho.home.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.germanofilho.core.helper.Resource
import com.germanofilho.core.viewmodel.BaseViewModel
import com.germanofilho.home.repository.HomeRepository
import com.germanofilho.network.feature.repositorylist.model.entity.GitRepositoryResponse
import kotlinx.coroutines.launch

class HomeViewModelImpl(private val repository: HomeRepository) : BaseViewModel(), HomeViewModel {

    val gitRepositoryList = MutableLiveData<Resource<GitRepositoryResponse>>()

    override fun getRepositoryList(page: Int) {
        viewModelScope.launch {
            gitRepositoryList.loading(true)
            try {
                gitRepositoryList.success(repository.getRepositoryList(1))
            } catch (e: Exception) {
                gitRepositoryList.error(e)
            } finally {
                gitRepositoryList.loading(false)
            }
        }
    }
}