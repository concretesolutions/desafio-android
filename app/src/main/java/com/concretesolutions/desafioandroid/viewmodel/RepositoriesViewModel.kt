package com.concretesolutions.desafioandroid.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.content.Context
import com.concretesolutions.desafioandroid.R
import com.concretesolutions.desafioandroid.helpers.NetworkHelper
import com.concretesolutions.desafioandroid.model.Repositories
import com.concretesolutions.desafioandroid.service.RepositoryService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositoriesViewModel {

    private val context: Context
    private var repositories: MutableLiveData<Repositories> = MutableLiveData()
    private var status: MutableLiveData<RepositorieStatus> = MutableLiveData()
    private val repositoriesService = NetworkHelper.getRetrofitInstanceGitHub()
        .create(RepositoryService::class.java)

    constructor(ctx: Context) {
        context = ctx
        status.value = RepositorieStatus(false, "")
    }

    fun getRepos(): MutableLiveData<Repositories> {
        return repositories
    }

    fun getStatus(): MutableLiveData<RepositorieStatus> {
        return status
    }

    fun loadRepos(searchTerm: String, sortType: String, page: Number) {

        repositoriesService.getRepositories(searchTerm, sortType, page)
            .enqueue(object : Callback<Repositories> {
                override fun onFailure(call: Call<Repositories>, t: Throwable) {
                    status.value = RepositorieStatus(status.value!!.finished, context.getString(R.string.error_load_repos))
                }
                override fun onResponse(
                    call: Call<Repositories>,
                    response: Response<Repositories>
                ) {
                    val result = response.body()
                    if (result != null && result.repositories.count() > 0) {
                        repositories.value = result
                    } else {
                        status.value = RepositorieStatus(true, "")
                    }
                }
            })
    }

    class RepositorieStatus(val finished: Boolean, val message: String)

}