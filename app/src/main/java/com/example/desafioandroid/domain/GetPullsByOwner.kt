package com.example.desafioandroid.domain

import com.example.desafioandroid.data.PullRepository
import com.example.desafioandroid.data.model.PullModel
import javax.inject.Inject

class GetPullsByOwner @Inject constructor(private val repository: PullRepository) {

    suspend operator fun invoke(owner: String, repo: String): List<PullModel>? =
        repository.getPullByOwner(owner, repo)
}