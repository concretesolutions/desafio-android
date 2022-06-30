package com.example.desafioandroid.data

import com.example.desafioandroid.data.model.RepositoriesModel
import com.example.desafioandroid.data.model.RepositoriesProvider
import com.example.desafioandroid.data.network.RepositoriesService

class RepositoriesRepository {

    private val api = RepositoriesService()

    suspend fun getRepositories(): List<RepositoriesModel>{
        val response = api.getRepositories()
        RepositoriesProvider.repositoriesList = response
        return response
    }
}