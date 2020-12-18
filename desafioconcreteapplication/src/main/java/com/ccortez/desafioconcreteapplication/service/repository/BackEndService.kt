package com.ccortez.desafioconcreteapplication.service.repository

import androidx.lifecycle.LiveData
import com.ccortez.desafioconcreteapplication.BuildConfig
import com.ccortez.desafioconcreteapplication.service.model.PullRequest
import com.ccortez.desafioconcreteapplication.service.model.Repo
import com.ccortez.desafioconcreteapplication.service.model.Repositories
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface BackEndService {

    @GET("search/repositories?q=language:Java&sort=stars&page=1")
    fun getRepos(): LiveData<ApiResponse<List<Repo>>>

    @GET("search/repositories?q=language:Java&sort=stars&page=1")
    fun repositories(): Call<Repositories>

    // https://api.github.com/repos/CyC2018/CS-Notes/pulls
    @GET(HTTP_API_URL + "repos/{full_name}/pulls")
    fun getRepositoryPulls(@Path("full_name", encoded = true) id: String?): Call<List<PullRequest>?>?

    companion object {
        const val HTTP_API_URL =
            BuildConfig.API_BASE_URL
    }
}