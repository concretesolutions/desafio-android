package com.dobler.desafio_android.data.model


data class GithubRepository(
    val id: Int,
    val name: String,
    val owner: User,
    val description: String,
    val total_stars: Int,
    val total_forks: Int)

