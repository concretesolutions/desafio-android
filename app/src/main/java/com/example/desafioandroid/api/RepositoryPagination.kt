package com.example.desafioandroid.api

import android.app.Activity
import android.arch.paging.PageKeyedDataSource
import android.arch.paging.PagedList
import android.content.Context
import android.util.Log
import com.example.desafioandroid.ChallengeApplication
import com.example.desafioandroid.schemas.RepositoryItem
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class RepositoryPagination(private val activity: Activity) : PageKeyedDataSource<String, RepositoryItem>(){
    val TAG = javaClass.simpleName

    private val challengeApplication = ChallengeApplication()[activity.baseContext]
    val apiService: ApiInterface = challengeApplication.apiService!!

    override fun loadInitial(params: LoadInitialParams<String>, callback: LoadInitialCallback<String, RepositoryItem>) {
        GlobalScope.launch {
            try {
                val response = apiService.getRepositories("Java", "stars",1).await()
                when{
                    response.isSuccessful-> {
                        val listing = response.body()!!.items
                        callback.onResult(listing ?: listOf(), "1", "2")                    }
                }
            }catch (exception : Exception){
                Log.e("PostsDataSource", "Failed to fetch data!")
            }
        }
    }

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, RepositoryItem>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<String, RepositoryItem>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }




}