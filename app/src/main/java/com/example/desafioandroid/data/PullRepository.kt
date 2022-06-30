package com.example.desafioandroid.data

import android.util.Log
import com.example.desafioandroid.data.model.ApiProvider
import com.example.desafioandroid.data.model.PullModel
import com.example.desafioandroid.data.network.ApiService

class PullRepository {

    private val api = ApiService()

    suspend fun getPullByOwner(owner: String, repo: String): List<PullModel> {
        val response = api.getPullByOwner(owner, repo)
        ApiProvider.pullList = response
        return response
    }
}