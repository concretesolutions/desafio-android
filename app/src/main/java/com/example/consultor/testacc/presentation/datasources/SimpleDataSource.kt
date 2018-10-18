package com.example.consultor.testacc.presentation.datasources

import android.arch.paging.PageKeyedDataSource
import android.util.Log
import com.example.consultor.testacc.TestACApp
import com.example.consultor.testacc.data.pojos.RepoPage
import com.example.consultor.testacc.data.pojos.Repository
import com.example.consultor.testacc.data.retrofit.ApiGithub
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SimpleDataSource : PageKeyedDataSource<Int, Repository>() {

    //se encarga de cargar la data inicial en este caso la pagina 1 y procede a aumentar las paginas
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Repository>) {
        val initPage = 1
        val serverRequest = TestACApp.retrofit?.create(ApiGithub::class.java)
        val responseGithub = serverRequest?.getRepositoriesAt(initPage)
        responseGithub?.enqueue(object : Callback<RepoPage> {
            override fun onFailure(call: Call<RepoPage>, t: Throwable) {
                Log.e("Get Repo Error", t.message)


            }

            override fun onResponse(call: Call<RepoPage>, response: Response<RepoPage>) {

                Log.i("Get Repo value", response.body()?.items?.size.toString())
                callback.onResult(response.body()!!.items, null, initPage + 1)
            }

        })


    }
   //se encarga de cargar la siguiente pagina cuando alcanza el  nivel indicado
    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Repository>) {

        val serverRequest = TestACApp.retrofit?.create(ApiGithub::class.java)
        Log.i("Params key", params.key?.toString())

        val responseGithub = serverRequest?.getRepositoriesAt(params.key)
        responseGithub?.enqueue(object : Callback<RepoPage> {
            override fun onFailure(call: Call<RepoPage>, t: Throwable) {
                Log.e("Get Repo Error", t.message)


            }

            override fun onResponse(call: Call<RepoPage>, response: Response<RepoPage>) {

                Log.i("Get Repo value", response.body()?.items?.size.toString())


                if (response.body() != null) {
                    val key = params.key + 1

                    callback.onResult(response.body()!!.items, key)
                }else{

                }

            }

        })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Repository>) {
   //la data ya esta cargada asi que este metodo no lo usaremos

    }


}