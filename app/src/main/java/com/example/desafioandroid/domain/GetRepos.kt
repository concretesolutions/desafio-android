package com.example.desafioandroid.domain

import com.example.desafioandroid.data.ReposRepository
import com.example.desafioandroid.data.model.RepoModel
import com.example.desafioandroid.data.model.SearchModel
import javax.inject.Inject

class GetRepos @Inject constructor(private val repository: ReposRepository) {

    suspend operator fun invoke(query: String, page: Int): List<RepoModel> =
        repository.searchRepositories(query,page)
}