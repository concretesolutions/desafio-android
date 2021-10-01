package com.concrete.challenge.utils

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.concrete.challenge.domain.io.response.RepositoriesResponse
import com.concrete.challenge.utils.Constants.TAG
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

fun ViewModel.callService(
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