package com.concrete.andresdavid.desafioandroid.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.concrete.andresdavid.desafioandroid.model.Repository
import com.concrete.andresdavid.desafioandroid.model.Resource
import com.concrete.andresdavid.desafioandroid.repository.RepositoryRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class RepositoryListViewModel : ViewModel() {
    private val repository: RepositoryRepository = RepositoryRepository()
    private lateinit var repositoryData: MutableLiveData<Resource<List<Repository>>>
    private var page = 1

    fun getJavaRepositories(): LiveData<Resource<List<Repository>>> {
        if (!::repositoryData.isInitialized) {
            repositoryData = MutableLiveData()
            load()
        }

        return repositoryData
    }

    fun load() {
        repository.searchJavaRepositories(page)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ result ->
                    addItems(result)
                    ++page
                }, { error ->
                    this.repositoryData.value = Resource.error(error.message!!, mutableListOf())
                })
    }

    fun addItems(newItems: List<Repository>){
        val newResultList = mutableListOf<Repository>()
        if(page != 1) {
            newResultList.addAll(this.repositoryData.value?.data!!)
        }
        newResultList.addAll(newItems!!)
        this.repositoryData.value = Resource.success(newResultList)
    }

}