package com.example.desafioandroid.domain

import com.example.desafioandroid.data.RepositoriesRepository
import com.example.desafioandroid.data.model.RepositoriesModel
import javax.inject.Inject

class GetRepositories @Inject constructor(private val repository: RepositoriesRepository) {

    suspend operator fun invoke(): List<RepositoriesModel>? = repository.getRepositories()
}