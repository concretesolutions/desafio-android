package com.example.desafioandroid.domain

import com.example.desafioandroid.data.RepoRepository
import com.example.desafioandroid.data.model.RepoModel
import javax.inject.Inject

class GetRepoByOwner @Inject constructor(private val repository: RepoRepository) {

    suspend operator fun invoke(owner: String, repo: String): RepoModel? =
        repository.getRepoByOwner(owner, repo)
}