package com.abs.javarepos.model.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.abs.javarepos.model.Repo
import com.abs.javarepos.model.githubapi.GithubApi
import com.abs.javarepos.model.githubapi.RepoResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepoRepository {

    var repos: MutableLiveData<ArrayList<Repo>> = MutableLiveData()

    fun getRepos(): LiveData<ArrayList<Repo>> {
        GithubApi.endpoints.getRepos(1).enqueue(object : Callback<RepoResponse> {
            override fun onResponse(call: Call<RepoResponse>, response: Response<RepoResponse>) {
                response.body()?.let {
                    repos.postValue(ArrayList(it.items))
                }
            }

            override fun onFailure(call: Call<RepoResponse>, t: Throwable) {
                print("ERROR: $t")
            }
        })

        return repos
    }
}