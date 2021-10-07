package com.concrete.challenge.utils

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.concrete.challenge.data.PullRequestEntity
import com.concrete.challenge.data.UserEntity
import com.concrete.challenge.domain.io.response.RepositoriesResponse
import com.concrete.challenge.utils.Constants.TAG
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

fun ViewModel.callServiceRepositories(
    liveData: MutableLiveData<RepositoriesResponse>,
    block: suspend () -> RepositoriesResponse
) {
    viewModelScope.launch(Dispatchers.Main) {
        runCatching {
            withContext(Dispatchers.IO) { block.invoke() }
        }.onSuccess { response ->
            liveData.postValue(response)
        }.onFailure { throwable ->
            liveData.postValue(null)
            Log.i(TAG, throwable.toString())
        }
    }
}

fun ViewModel.callServiceUser(
    liveData: MutableLiveData<List<UserEntity>>,
    block: suspend () -> List<UserEntity>
) {
    viewModelScope.launch(Dispatchers.Main) {
        runCatching {
            withContext(Dispatchers.IO) { block.invoke() }
        }.onSuccess { response ->
            liveData.postValue(response)
            Log.i(TAG, response.toString())
        }.onFailure { throwable ->
            liveData.postValue(null)
            Log.i(TAG, throwable.toString())
        }
    }
}

fun ViewModel.callServicePullRequests(
    liveData: MutableLiveData<List<PullRequestEntity>>,
    block: suspend () -> List<PullRequestEntity>
) {
    viewModelScope.launch(Dispatchers.Main) {
        runCatching {
            withContext(Dispatchers.IO) { block.invoke() }
        }.onSuccess { response ->
            liveData.postValue(response)
            Log.i(TAG, response.toString())
        }.onFailure { throwable ->
            liveData.postValue(null)
            Log.i(TAG, throwable.toString())
        }
    }
}