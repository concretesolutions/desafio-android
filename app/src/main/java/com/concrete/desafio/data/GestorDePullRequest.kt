package com.concrete.desafio.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.concrete.desafio.data.interfaces.GitHubService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class GestorDePullRequest(val gitHubServiceAPI: GitHubServiceAPI) {

    private var mPullRequest: MutableLiveData<MutableList<PullRequest>> = MutableLiveData()

    fun getPullRequest() = mPullRequest

    fun buscarPullRequest(repositorio: String, autor: String) {

        val gitHubServicePullRequest: GitHubService = gitHubServiceAPI.getGitHubService()

        val call = gitHubServicePullRequest.listarPullRequest(repositorio, autor)

        CoroutineScope(Dispatchers.IO).launch {

            call.enqueue(object : Callback<List<PullRequest>> {

                override fun onResponse(call: Call<List<PullRequest>>?, response: Response<List<PullRequest>>?) {

                    if(response!!.body() != null){

                        var tmp = mPullRequest.value

                        if(tmp == null){
                            tmp = mutableListOf()
                        }

                        tmp.addAll(response.body() as ArrayList<PullRequest>)
                        mPullRequest.postValue(tmp)
                    }
                }

                override fun onFailure(call: Call<List<PullRequest>>?, t: Throwable?) {
                    Log.d("TAG", "ERROR")
                }
            })
        }
    }



}