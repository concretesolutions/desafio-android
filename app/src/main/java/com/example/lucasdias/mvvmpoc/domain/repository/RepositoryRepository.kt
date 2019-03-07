package com.example.lucasdias.mvvmpoc.domain.repository

import android.arch.lifecycle.LiveData
import com.example.lucasdias.mvvmpoc.domain.entity.Repository
import com.example.lucasdias.mvvmpoc.utils.RequestPermissionStatusEnum

interface RepositoryRepository {

    fun getRepositoryList(): LiveData<List<Repository>>?

    fun getRequestStatus(): LiveData<RequestPermissionStatusEnum>?

    fun loadRepositoryPageFromApi(page: Int)

}