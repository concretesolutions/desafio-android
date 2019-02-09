package com.accenture.desafioandroid.data.datasource
import android.arch.paging.PageKeyedDataSource
import android.util.Log
import com.accenture.desafioandroid.data.network.Network
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import com.accenture.desafioandroid.mvvm.model.Item
import com.accenture.desafioandroid.mvvm.model.RepositoryResponse


class RepositoryDataSource: PageKeyedDataSource<Int, Item>() {

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Item>) {
        val page = 1
        val inter = Network.create()
        Log.i("RepositoryDataSource","calling RepositoryDataSource")
        inter.getRepository("Java","stars", page).enqueue(object : Callback<RepositoryResponse> {
            override fun onResponse(call: Call<RepositoryResponse>, response: Response<RepositoryResponse>) {
                val searchModel = response.body()

                if (searchModel == null) {
                    onFailure(call, HttpException(response))
                    return
                }

                callback.onResult(response.body()!!.items!!, 0, searchModel.totalCount!!, null, page + 1)
            }

            override fun onFailure(call: Call<RepositoryResponse>, t: Throwable) {
            }
        })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Item>) {
        val page = params.key
        val inter = Network.create()
        inter.getRepository("Java","stars", page).enqueue(object : Callback<RepositoryResponse> {
            override fun onResponse(call: Call<RepositoryResponse>, response: Response<RepositoryResponse>) {
                val searchModel = response.body()

                if (searchModel ==
                    null
                ) {
                    onFailure(call, HttpException(response))
                    return
                }

                callback.onResult(
                    searchModel.items!!, // List of data items
                    // Next Page key (Used at the next request). Return `null` if this is the last page.
                    page + 1
                )
            }

            override fun onFailure(call: Call<RepositoryResponse>, t: Throwable) {
            }
        })


    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Item>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}