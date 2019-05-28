package com.jmc.desafioandroidkotlin.domain.usecase

import com.jmc.desafioandroidkotlin.domain.model.PullModel
import com.jmc.desafioandroidkotlin.domain.repository.GithubRepository
import com.jmc.desafioandroidkotlin.domain.usecase.coroutines.ResultUseCase
import kotlinx.coroutines.Dispatchers

open class GetPullsUseCase(
    private val githubRepository: GithubRepository
) : ResultUseCase<String, List<PullModel>>(
    backgroundContext = Dispatchers.IO,
    foregroundContext = Dispatchers.Main
) {
    override suspend fun executeOnBackground(params: String): List<PullModel>? {
        return githubRepository.getPulls(repository = params)
    }
}