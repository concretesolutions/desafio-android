package com.example.desafioandroidapp.data

import com.example.desafioandroidapp.data.dto.Pull
import com.example.desafioandroidapp.data.dto.Repository

data class DesafioApiRepository(private val desafioApiDatasource: DesafioApiDataSource) {
    fun getRepositories(listener: RepositoryListener<Repository>, page: Int) {
        this.desafioApiDatasource.getRepositories(listener,page)
    }

    fun getPulls(listener: PullListener<List<Pull>>, owner: String, repository: String) {
        this.desafioApiDatasource.getPulls(listener, owner, repository)
    }
}