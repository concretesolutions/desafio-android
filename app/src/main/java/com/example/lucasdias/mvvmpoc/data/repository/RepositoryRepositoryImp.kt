package com.example.lucasdias.mvvmpoc.data.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.lucasdias.mvvmpoc.data.db.AppDatabase
import com.example.lucasdias.mvvmpoc.data.service.RepositoryService
import com.example.lucasdias.mvvmpoc.domain.entity.Repository
import com.example.lucasdias.mvvmpoc.domain.repository.RepositoryRepository
import com.example.lucasdias.mvvmpoc.utils.RequestPermissionStatusEnum
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import timber.log.Timber

class RepositoryRepositoryImp(private val service: RepositoryService, private val database: AppDatabase): RepositoryRepository {

    private var firstRequestStatus = true
    private var requestActualStatus : MutableLiveData<RequestPermissionStatusEnum> = MutableLiveData()

    override fun getRepositoryList(): LiveData<List<Repository>>? {
        return database.repositoryDao().getRepositoryList()
    }

    override fun getRequestStatus(): LiveData<RequestPermissionStatusEnum>? {
        if (firstRequestStatus){
            requestActualStatus.postValue(RequestPermissionStatusEnum.UNKNOWN)
            firstRequestStatus = false
        }
        return  requestActualStatus
    }

    override fun loadRepositoryPageFromApi(page: Int) {

        GlobalScope.async {
            val repositoryListResponse = service.loadRepositoryPageFromApi(page).await()
            val code = repositoryListResponse.code()
            Timber.i("response body ${repositoryListResponse.body()}")

            when (code) {
                in 200..300 -> requestActualStatus.postValue(RequestPermissionStatusEnum.SUCCESS)
                in 400..500 -> requestActualStatus.postValue(RequestPermissionStatusEnum.FORBIDDEN)
                else -> requestActualStatus.postValue(RequestPermissionStatusEnum.UNKNOWN)
            }


            if(requestActualStatus != RequestPermissionStatusEnum.FORBIDDEN){
                repositoryListResponse.body()?.repositories?.forEach {
                    it.pageNumber = page
                }
                database.repositoryDao().
                        insertRepositoryList(repositoryListResponse.body()?.repositories)
            }

        }
    }


}