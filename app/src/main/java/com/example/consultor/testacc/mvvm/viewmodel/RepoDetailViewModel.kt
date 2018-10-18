package com.example.consultor.testacc.mvvm.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.example.consultor.testacc.TestACApp
import com.example.consultor.testacc.data.pojos.PullModel
import com.example.consultor.testacc.data.pojos.RepoPage
import com.example.consultor.testacc.data.pojos.Repository
import com.example.consultor.testacc.data.retrofit.ApiGithub
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepoDetailViewModel : ViewModel() {

    var myList: MutableLiveData<MutableList<PullModel>> = MutableLiveData()

    var issuesCount: MutableLiveData<Int> = MutableLiveData()


    fun getPullRequests(repository: Repository) {
        val serverRequest = TestACApp.retrofit?.create(ApiGithub::class.java)
        val responseGithub = serverRequest?.getRepositoryPulls(repository.owner.name, repository.repoName)

        val responseIssuesGithub = serverRequest?.getRepositoryIssues(repository.owner.name, repository.repoName)
        responseIssuesGithub?.enqueue(object : Callback<MutableList<PullModel>> {
            override fun onFailure(call: Call<MutableList<PullModel>>, t: Throwable) {

                Log.e("Error issues", t.message)

            }

            override fun onResponse(call: Call<MutableList<PullModel>>, response: Response<MutableList<PullModel>>) {

                issuesCount.value = response.body()?.get(0)?.number
            }

        })
        responseGithub?.enqueue(object : Callback<MutableList<PullModel>> {
            override fun onFailure(call: Call<MutableList<PullModel>>, t: Throwable) {
                Log.e("Error pull", t.message)

            }

            override fun onResponse(call: Call<MutableList<PullModel>>, response: Response<MutableList<PullModel>>) {
                Log.e("Numero pulls", response.body()?.size.toString())
                myList.value = response.body()
            }

        })

    }

}
