package com.example.desafioandroid.data

import com.example.desafioandroid.data.model.ApiProvider
import com.example.desafioandroid.data.model.RepoModel
import com.example.desafioandroid.data.network.ApiService
import javax.inject.Inject

class ReposRepository @Inject constructor(private val api: ApiService, private val ApiProvider: ApiProvider) {

    suspend fun searchRepositories(query: String, page: Int): List<RepoModel>{
        val response = api.searchRepositories(query,page)
        ApiProvider.repositoriesList = response.items
        return response.items
    }
}