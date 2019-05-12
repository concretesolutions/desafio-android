package com.gdavidpb.github.domain.usecase

import com.gdavidpb.github.domain.repository.VCSRepository
import com.gdavidpb.github.domain.usecase.coroutines.CompletableUseCase
import com.gdavidpb.github.utils.SIZE_PAGE
import kotlinx.coroutines.Dispatchers

open class FetchRepositoriesUseCase(
    private val vcsRepository: VCSRepository
) : CompletableUseCase<Unit>(
    backgroundContext = Dispatchers.IO,
    foregroundContext = Dispatchers.Main
) {
    override suspend fun executeOnBackground(params: Unit) {
        val localCount = vcsRepository.getRepositoriesCount()
        val currentPage = (localCount / SIZE_PAGE).toInt()
        val nextPage = currentPage + 1

        vcsRepository.getRepositories(nextPage)
    }
}