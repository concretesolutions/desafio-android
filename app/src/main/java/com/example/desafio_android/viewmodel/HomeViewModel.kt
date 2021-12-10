package com.example.desafio_android.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.desafio_android.data.model.repositoriesInfo
import com.example.desafio_android.data.repositories.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository
): ViewModel() {

    fun getAllRepositoryList(): LiveData<AppResource<repositoriesInfo?>> {
        val liveData = MutableLiveData<AppResource<repositoriesInfo?>>()
        viewModelScope.launch(Dispatchers.IO) {
            liveData.postValue(AppResource.Loading())
            liveData.postValue(homeRepository.getAllRepositories())
        }
        return liveData
    }

}