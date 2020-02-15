package com.concrete.desafio_android.domain

class Repository (
    val name: String,
    val description: String,
    val stargazers_count: Int,
    val forks_count: Int,
    val owner: RepositoryOwner
)