package com.hotmail.fignunes.desafioconcretesolution.repository.remote.gitrepository.responses

data class GitRepositoryResponses(
    val total_count: Long,
    val incomplete_results: Boolean,
    val items: List<GitRepositoryItemsResponses>
)