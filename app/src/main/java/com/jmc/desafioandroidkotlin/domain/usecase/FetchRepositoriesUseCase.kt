package com.jmc.desafioandroidkotlin.domain.usecase

import com.jmc.desafioandroidkotlin.domain.repository.GithubRepository
import com.jmc.desafioandroidkotlin.domain.usecase.coroutines.CompletableUseCase
import kotlinx.coroutines.Dispatchers

open class FetchRepositoriesUseCase(
    private val githubRepository: GithubRepository
) : CompletableUseCase<Int>(
    backgroundContext = Dispatchers.IO,
    foregroundContext = Dispatchers.Main
) {
    override suspend fun executeOnBackground(params: Int) {
        githubRepository.getRepositories(page = params)
    }
}