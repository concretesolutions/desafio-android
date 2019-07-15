package com.pedrenrique.githubapp.core.net.services

import com.pedrenrique.githubapp.core.net.RepositoryResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface GithubService {
    @GET("search/repositories")
    fun list(@QueryMap params: Map<String, String>): Deferred<RepositoryResponse>
}