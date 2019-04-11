package com.example.desafioandroid.api

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.example.desafioandroid.schema.RepositoryItem
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class RepositoryPagination : PageKeyedDataSource<String, RepositoryItem>(){
    val TAG = javaClass.simpleName
    private val apiService = RetrofitClient.getClient().create(ApiInterface::class.java)

    override fun loadInitial(params: LoadInitialParams<String>, callback: LoadInitialCallback<String, RepositoryItem>) {
        GlobalScope.launch {
            try {
                val response = apiService.getRepositories("language:Java", "stars", 1).await()
                when{
                    response.isSuccessful-> {
                        val listing = response.body()!!.items
                        callback.onResult(listing ?: listOf(),null, "2")                    }
                }
            }catch (exception : Exception){
                Log.e(TAG, "Failed to fetch data!")
            }
        }
    }

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, RepositoryItem>) {
        GlobalScope.launch {
            try {
                val response = apiService.getRepositories("Java","starts", params.key.toInt()).await()
                when{
                    response.isSuccessful -> {
                        val listing = response.body()!!.items
                        val page : String = (params.key.toDouble() + 1).toString()
                        callback.onResult(listing ?: listOf(), page)
                    }
                }

            }catch (exception : Exception){
                Log.e(TAG, "Failed to fetch data!")
            }
        }
    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<String, RepositoryItem>) {
        GlobalScope.launch {
            try {
                val response = apiService.getRepositories("language:Java","starts", params.key.toInt()).await()
                when{
                    response.isSuccessful -> {
                        val listing = response.body()!!.items
                        val page : String = (params.key.toInt() + 1).toString()
                        callback.onResult(listing ?: listOf(), page)
                    }
                }

            }catch (exception : Exception){
                Log.e(TAG, "Failed to fetch data!")
            }
        }
    }




}