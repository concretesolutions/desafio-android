package com.mbstro.fmoyagonzalez.desafio_android

data class Repo(
        val name: String,
        val description: String,
        val forks_count: Int,
        val stargazers_count: Int,
        val owner: Owner
)

data class Owner(
        val login: String,
        val avatar_url: String
)