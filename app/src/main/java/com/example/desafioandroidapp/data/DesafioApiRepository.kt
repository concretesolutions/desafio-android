package com.example.desafioandroidapp.data

import com.example.desafioandroidapp.data.dto.Repository

data class DesafioApiRepository(private val desafioApiDatasource: DesafioApiDataSource) {
    fun getRepositories(listener: ResponseListener<Repository>, page: Int) {
        this.desafioApiDatasource.getRepositories(listener,page)
    }
}