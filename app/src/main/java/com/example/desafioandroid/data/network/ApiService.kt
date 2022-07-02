package com.example.desafioandroid.data.network

import android.util.Log
import com.example.desafioandroid.data.model.PullModel
import com.example.desafioandroid.data.model.RepoModel
import com.example.desafioandroid.data.model.RepositoriesModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class ApiService @Inject constructor(private val api: RepositoriesApiClient) {

    suspend fun getRepositories(): List<RepositoriesModel> {
        return withContext(Dispatchers.IO) {
            val response: Response<List<RepositoriesModel>> =
                api.getAllRepositories()
            response.body() ?: emptyList()
        }
    }

    suspend fun getPullByOwner(owner: String, repo: String): List<PullModel> {
        return withContext(Dispatchers.IO) {
            val response: Response<List<PullModel>> =
                api.getPullByOwner(owner, repo)
            response.body() ?: emptyList()
        }
    }

    suspend fun getRepoByOwner(owner: String, repo: String): RepoModel {
        return withContext(Dispatchers.IO) {
            val response: Response<RepoModel> =
                api.getRepoByOwner(owner, repo)
            Log.i("getRepoByOwner", response.toString())
                response.body()!!
        }
    }

}
