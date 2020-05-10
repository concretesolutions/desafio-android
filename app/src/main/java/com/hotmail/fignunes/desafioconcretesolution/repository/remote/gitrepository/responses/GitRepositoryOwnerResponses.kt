package com.hotmail.fignunes.desafioconcretesolution.repository.remote.gitrepository.responses

data class GitRepositoryOwnerResponses(
    val login: String,
    val id: Long,
    val avatar_url: String,
    val url: String?
)