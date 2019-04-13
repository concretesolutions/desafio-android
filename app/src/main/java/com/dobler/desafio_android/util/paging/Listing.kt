package com.dobler.desafio_android.util.paging

import androidx.lifecycle.LiveData
import androidx.paging.PagedList

data class Listing<T>(
    val pagedList: LiveData<PagedList<T>>,

    val networkState: LiveData<NetworkState>,

    val refreshState: LiveData<NetworkState>,

    val refresh: () -> Unit,

    val retry: () -> Unit
)