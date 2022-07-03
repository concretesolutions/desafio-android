package com.example.desafioandroid.domain

import com.example.desafioandroid.data.ReposRepository
import com.example.desafioandroid.data.database.entities.toDatabase
import com.example.desafioandroid.domain.model.Repo
import javax.inject.Inject

class GetRepos @Inject constructor(private val repository: ReposRepository) {

    suspend operator fun invoke(query: String, page: Int): List<Repo> {
        val repo = repository.searchRepositoriesApi(query, page)

        return if (repo.isNotEmpty()) {
            repository.deleteAllRepos()
            repository.insertRepos(repo.map { it.toDatabase() })
            repo
        } else {
            repository.searchRepositoriesDb()
        }
    }
}