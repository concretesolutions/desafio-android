package com.hotmail.fignunes.desafioconcretesolution.repository.remote.gitrepository.responses

data class GitRepositoryItemsResponses(
    val id: Long,
    val name: String,
    val full_name: String?,
    val owner: GitRepositoryOwnerResponses,
    val description: String?,
    val stargazers_count: Long?,
    val forks_count: Long?
)