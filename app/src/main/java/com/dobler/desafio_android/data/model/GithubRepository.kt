package com.dobler.desafio_android.data.model


data class GithubRepository(
    val id: Int,
    val name: String,
    val owner: User,
    val full_name: String,
    val description: String,
    val stargazers_count: Int,
    val forks_count: Int)

