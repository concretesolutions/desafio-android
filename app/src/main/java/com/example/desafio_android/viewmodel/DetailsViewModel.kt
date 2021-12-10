package com.example.desafio_android.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.desafio_android.data.model.repositoriesInfo
import com.example.desafio_android.data.pullmodel.RepositoryPullsItem
import com.example.desafio_android.data.repositories.DetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val detailRepository: DetailRepository
)  : ViewModel() {

    fun getAllRepositoryPulls(ownerName: String, repoName : String): LiveData<AppResource<List<RepositoryPullsItem>?>> {
        val liveData = MutableLiveData<AppResource<List<RepositoryPullsItem>?>>()
        viewModelScope.launch(Dispatchers.IO) {
            liveData.postValue(AppResource.Loading())
            liveData.postValue(detailRepository.getRepositoryPulls(ownerName, repoName))
        }
        return liveData
    }
}