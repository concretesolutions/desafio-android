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
    private val repositoriesService = NetworkHelper.getRetrofitInstanceGitHub()
        .create(RepositoryService::class.java)

    fun getRepos(): MutableLiveData<Repositories> {
        return repositories
    }

    fun loadRepos(searchTerm: String, sortType: String, page: Number) {
        repositoriesService.getRepositories(searchTerm, sortType, page)
            .enqueue(object : Callback<Repositories> {
                override fun onFailure(call: Call<Repositories>, t: Throwable) {
                    Log.e("Repos", t.message)
                }

                override fun onResponse(call: Call<Repositories>, response: Response<Repositories>) {
                    response.body()?.let {
                        repositories.value = it
                    }

                }
            })
    }


}