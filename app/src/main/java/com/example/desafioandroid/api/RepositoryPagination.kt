package com.example.desafioandroid.api

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.example.desafioandroid.schema.RepositoryItem
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class RepositoryPagination : PageKeyedDataSource<Int, RepositoryItem>(){
    val TAG = javaClass.simpleName
    private val apiService = RetrofitClient.getClient().create(ApiInterface::class.java)

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, RepositoryItem>) {
        GlobalScope.launch {
            try {
                val response = apiService.getRepositories("language:Java", "stars", 1).await()
                when{
                    response.isSuccessful-> {
                        val listing = response.body()!!.items
                        callback.onResult(listing ?: listOf(),null, 2)

                    }else ->{
                        Log.e(TAG, "Failure")
                    }
                }
            }catch (exception : Throwable){
                Log.e(TAG, "Failed to fetch data!")
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, RepositoryItem>) {
        GlobalScope.launch {
            try {
                val response = apiService.getRepositories("Java","starts", params.key).await()
                when{
                    response.isSuccessful -> {
                        val listing = response.body()!!.items
                        val page = params.key + 1
                        Log.e(TAG,page.toString())
                        callback.onResult(listing ?: listOf(), page)
                    }
                }

            }catch (exception : Exception){
                Log.e(TAG, "Failed to fetch data!")
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, RepositoryItem>) {
            GlobalScope.launch {
                try {
                    val response = apiService.getRepositories("language:Java","starts", params.key).await()
                    when{
                        response.isSuccessful -> {
                            val listing = response.body()!!.items
                            val page = params.key.toInt() - 1
                            callback.onResult(listing ?: listOf(), page)
                        }
                    }

                }catch (exception : Exception){
                    Log.e(TAG, "Failed to fetch data!")
                }
            }
    }

}