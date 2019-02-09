package com.accenture.desafioandroid.data.datasource

import android.arch.paging.PageKeyedDataSource
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import com.accenture.desafioandroid.App
import com.accenture.desafioandroid.data.network.Network
import com.accenture.desafioandroid.mvvm.model.PullRequest
import com.accenture.desafioandroid.utils.MySharedPreferences


class PullRequestDataSource : PageKeyedDataSource<Int, PullRequest>() {
    private val TAG = PullRequestDataSource::class.java.simpleName

    private var myPreferences = MySharedPreferences(App.getContext())

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, PullRequest>) {

        val owner = myPreferences.getOwner()
        val repo = myPreferences.getRepo()

        Log.i(TAG, "myPreferences->" + myPreferences.getOwner() + "->" + myPreferences.getRepo())
        val page = 1
        val inter = Network.create()
        inter.getPullRequest(owner, repo).enqueue(object : Callback<MutableList<PullRequest>> {
            override fun onResponse(call: Call<MutableList<PullRequest>>, response: Response<MutableList<PullRequest>>) {
                val searchModel = response.body()
                if (searchModel ==
                    null
                ) {
                    onFailure(call, HttpException(response))
                    return
                }

                callback.onResult(response.body()!!, 0, response.body()!!.size, null, page + 1)
            }


            override fun onFailure(call: Call<MutableList<PullRequest>>, t: Throwable) {
                Log.e(TAG, t.message)
            }
        })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, PullRequest>) {
        val owner = myPreferences.getOwner()
        val repo = myPreferences.getRepo()
        val page = params.key
        val inter = Network.create()
        inter.getPullRequest(owner, repo).enqueue(object : Callback<MutableList<PullRequest>> {
            override fun onResponse(call: Call<MutableList<PullRequest>>, response: Response<MutableList<PullRequest>>) {
                val searchModel = response.body()

                if (searchModel ==
                    null
                ) {
                    onFailure(call, HttpException(response))
                    return
                }

                callback.onResult(
                    searchModel, // List of data items
                    // Next Page key (Used at the next request). Return `null` if this is the last page.
                    page + 1
                )
            }

            override fun onFailure(call: Call<MutableList<PullRequest>>, t: Throwable) {
                Log.e(TAG, t.message)

            }
        })


    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, PullRequest>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}