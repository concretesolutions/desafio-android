package com.concrete.desafio.data

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.concrete.desafio.data.interfaces.GitHubService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class GestorDeRepositorios(val gitHubServiceAPI: GitHubServiceAPI) {

    private var mRepositorios: MutableLiveData<MutableList<Repositorio>> = MutableLiveData()

    fun getRepositorios() = mRepositorios

    fun buscarRepositorios() {

        val gitHubServiceRepositorios: GitHubService = gitHubServiceAPI.getGitHubService()

        val call = gitHubServiceRepositorios.listarRepositorios()

        CoroutineScope(Dispatchers.IO).launch {

            call.enqueue(object : Callback<Repositorios> {

                override fun onResponse(call: Call<Repositorios>?, response: Response<Repositorios>?) {

                    if(response!!.body() != null){

                        var tmp = mRepositorios.value

                        if(tmp == null){
                            tmp = mutableListOf()
                        }

                        tmp.addAll(response.body()?.repositorios as ArrayList<Repositorio>)
                        mRepositorios.postValue(tmp)

                    }

                }

                override fun onFailure(call: Call<Repositorios>?, t: Throwable?) {

                    Log.d("TAG", "ERROR")
                }
            })
        }
    }



}