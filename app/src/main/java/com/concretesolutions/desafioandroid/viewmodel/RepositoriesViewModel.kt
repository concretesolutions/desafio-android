package com.concretesolutions.desafioandroid.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.concretesolutions.desafioandroid.helpers.NetworkHelper
import com.concretesolutions.desafioandroid.model.Repositories
import com.concretesolutions.desafioandroid.service.RepositoryService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositoriesViewModel {

    private var repositories: MutableLiveData<Repositories> = MutableLiveData()
    private var finshed: MutableLiveData<Boolean> = MutableLiveData()
    private val repositoriesService = NetworkHelper.getRetrofitInstanceGitHub()
        .create(RepositoryService::class.java)

    constructor() {
        finshed.value = false
    }

    fun getRepos(): MutableLiveData<Repositories> {
        return repositories
    }

    fun isFinished(): MutableLiveData<Boolean> {
        return finshed
    }

    fun loadRepos(searchTerm: String, sortType: String, page: Number) {
        Log.i("teste", "vai")
        repositoriesService.getRepositories(searchTerm, sortType, page)
            .enqueue(object : Callback<Repositories> {
                override fun onFailure(call: Call<Repositories>, t: Throwable) {
                    Log.e("teste", t.message)
                }

                override fun onResponse(call: Call<Repositories>, response: Response<Repositories>) {
                    Log.i("teste", "chegou")
                    response.body()?.let {
                        repositories.value = it
                        Log.i("teste", it.repositories.count().toString())
                        if(it.repositories.count() == 0) finshed.value = true
                    }

                }
            })
    }


}