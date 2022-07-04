package com.example.desafioandroid.data

import com.example.desafioandroid.data.model.ApiProvider
import com.example.desafioandroid.data.model.PullModel
import com.example.desafioandroid.data.network.ApiService
import javax.inject.Inject

class PullRepository @Inject constructor(private val api: ApiService,private val ApiProvider: ApiProvider) {

    suspend fun getPullByOwner(owner: String, repo: String): List<PullModel> {
        val response = api.getPullByOwner(owner, repo)
        ApiProvider.pullList = response
        return response
    }
}