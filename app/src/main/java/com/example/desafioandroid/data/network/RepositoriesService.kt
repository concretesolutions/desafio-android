package com.example.desafioandroid.data.network

import com.example.desafioandroid.core.RetrofitHelper
import com.example.desafioandroid.data.model.RepositoriesModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class RepositoriesService {

    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getRepositories(): List<RepositoriesModel> {
        return withContext(Dispatchers.IO) {
            val response: Response<List<RepositoriesModel>> =
                retrofit.create(RepositoriesApiClient::class.java).getAllRepositories()
            response.body() ?: emptyList()
        }
    }
}