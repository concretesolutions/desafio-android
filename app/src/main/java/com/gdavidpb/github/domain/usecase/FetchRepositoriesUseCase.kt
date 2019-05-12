package com.gdavidpb.github.domain.usecase

import com.gdavidpb.github.domain.repository.VCSRepository
import com.gdavidpb.github.domain.usecase.coroutines.CompletableUseCase
import kotlinx.coroutines.Dispatchers

open class FetchRepositoriesUseCase(
    private val vcsRepository: VCSRepository
) : CompletableUseCase<Int>(
    backgroundContext = Dispatchers.IO,
    foregroundContext = Dispatchers.Main
) {
    override suspend fun executeOnBackground(params: Int) {
        vcsRepository.getRepositories(page = params)
    }
}