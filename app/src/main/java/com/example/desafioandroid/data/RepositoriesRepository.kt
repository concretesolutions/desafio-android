package com.example.desafioandroid.data

import com.example.desafioandroid.data.model.RepositoriesModel
import com.example.desafioandroid.data.model.ApiProvider
import com.example.desafioandroid.data.network.ApiService

class RepositoriesRepository {

    private val api = ApiService()

    suspend fun getRepositories(): List<RepositoriesModel>{
        val response = api.getRepositories()
        ApiProvider.repositoriesList = response
        return response
    }
}