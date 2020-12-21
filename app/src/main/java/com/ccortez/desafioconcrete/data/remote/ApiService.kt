package com.ccortez.desafioconcrete.data.remote

import androidx.lifecycle.LiveData
import com.ccortez.desafioconcrete.BuildConfig
import com.ccortez.desafioconcrete.data.repository.ApiResponse
import com.ccortez.desafioconcrete.model.PullRequest
import com.ccortez.desafioconcrete.model.Repo
import com.ccortez.desafioconcrete.model.Repositories
import com.ccortez.desafioconcrete.model.UserListResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("api/users")
    suspend fun fetchUsers(
        @Query("page") number: Int,
        @Query("per_page") per_page: Int
    ): Response<UserListResponse>


    @GET("search/repositories?q=language:Java&sort=stars&page=1")
    fun getRepos(): LiveData<ApiResponse<List<Repo>>>

    // search/repositories?q=language:Java&sort=stars&page=1
    @GET("search/repositories?q=language:Java&sort=stars")
    suspend fun fetchItems(
        @Query("page") number: Int
    ): Response<Repositories>

    // https://api.github.com/repos/CyC2018/CS-Notes/pulls
    @GET(HTTP_API_URL + "repos/{full_name}/pulls")
    fun getRepositoryPulls(@Path("full_name", encoded = true) id: String?): Call<List<PullRequest>?>?


    companion object {
        const val HTTP_API_URL =
            BuildConfig.API_BASE_URL
    }

}