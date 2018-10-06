package com.concrete.andresdavid.desafioandroid.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.concrete.andresdavid.desafioandroid.model.Repository
import com.concrete.andresdavid.desafioandroid.repository.RepositoryRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class RepositoryListViewModel : ViewModel() {
    private val repository: RepositoryRepository = RepositoryRepository()
    private lateinit var repositories: MutableLiveData<List<Repository>>

    fun getJavaRepositories(): LiveData<List<Repository>> {
        if (!::repositories.isInitialized) {
            repositories = MutableLiveData()
            loadRepositories()
        }
        return repositories
    }

    private fun loadRepositories() {
        repository.searchUsers(1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ result ->
                    repositories.value = result
                    Log.d("Result", "There are ${result.size} Java developers in Lagos")
                }, { error ->
                    error.printStackTrace()
                    //TODO("inform page error")
                })
    }

    private fun appendRepositories(page: Int){
        //TODO("add pagination")
    }
}