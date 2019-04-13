package com.dobler.desafio_android.data.model

data class RepositoryPullRequest(
    val id: Int,
    val body: String,
    val title: String,
    val updated_at: String,
    val user: User
)
