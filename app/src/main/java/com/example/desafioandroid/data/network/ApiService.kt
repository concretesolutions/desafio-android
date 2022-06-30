package com.example.desafioandroid.data.network

import android.util.Log
import com.example.desafioandroid.core.RetrofitHelper
import com.example.desafioandroid.data.model.PullModel
import com.example.desafioandroid.data.model.RepositoriesModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class ApiService {

    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getRepositories(): List<RepositoriesModel> {
        return withContext(Dispatchers.IO) {
            val response: Response<List<RepositoriesModel>> =
                retrofit.create(RepositoriesApiClient::class.java).getAllRepositories()
            response.body() ?: emptyList()
        }
    }

     suspend fun getPullByOwner(owner: String,repo: String): List<PullModel> {
        return withContext(Dispatchers.IO) {
            val response: Response<List<PullModel>> =
            retrofit.create(RepositoriesApiClient::class.java).getPullByOwner(owner,repo)
            response.body() ?: emptyList()
        }
    }
}