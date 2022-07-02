package com.example.desafioandroid.data

import com.example.desafioandroid.data.model.ApiProvider
import com.example.desafioandroid.data.model.RepoModel
import com.example.desafioandroid.data.network.ApiService
import retrofit2.Response
import javax.inject.Inject

class RepoRepository @Inject constructor(private val api: ApiService, private val ApiProvider: ApiProvider) {

    suspend fun getRepoByOwner(owner: String, repo: String): RepoModel {
        val response = api.getRepoByOwner(owner,repo)
        ApiProvider.repository = response
        return response
    }
}