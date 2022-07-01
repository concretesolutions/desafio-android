package com.example.desafioandroid.data

import com.example.desafioandroid.data.model.RepositoriesModel
import com.example.desafioandroid.data.model.ApiProvider
import com.example.desafioandroid.data.network.ApiService
import javax.inject.Inject

class RepositoriesRepository @Inject constructor(private val api: ApiService, private val ApiProvider: ApiProvider) {

    suspend fun getRepositories(): List<RepositoriesModel>{
        val response = api.getRepositories()
        ApiProvider.repositoriesList = response
        return response
    }
}