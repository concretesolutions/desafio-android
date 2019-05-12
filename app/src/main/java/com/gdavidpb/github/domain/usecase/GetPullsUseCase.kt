package com.gdavidpb.github.domain.usecase

import com.gdavidpb.github.domain.model.Pull
import com.gdavidpb.github.domain.repository.VCSRepository
import com.gdavidpb.github.domain.usecase.coroutines.ResultUseCase
import kotlinx.coroutines.Dispatchers

open class GetPullsUseCase(
    private val vcsRepository: VCSRepository
) : ResultUseCase<String, List<Pull>>(
    backgroundContext = Dispatchers.IO,
    foregroundContext = Dispatchers.Main
) {
    override suspend fun executeOnBackground(params: String): List<Pull>? {
        return vcsRepository.getPulls(repository = params)
    }
}