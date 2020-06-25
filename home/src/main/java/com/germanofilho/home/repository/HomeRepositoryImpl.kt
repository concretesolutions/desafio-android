package com.germanofilho.home.repository

import android.content.Context
import com.germanofilho.network.factory.ApiFactory
import com.germanofilho.network.feature.repositorylist.data.source.remote.api.GitRepositoryApi
import com.germanofilho.network.feature.repositorylist.model.entity.GitRepositoryResponse

class HomeRepositoryImpl(private val context: Context) : HomeRepository {

    private val service by lazy { ApiFactory.request(context, GitRepositoryApi::class.java) }

    override suspend fun getRepositoryList(page: Int) : GitRepositoryResponse {
        return service.getRepositoryList(page = page)
    }
}