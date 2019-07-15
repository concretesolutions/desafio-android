package com.pedrenrique.githubapp.core.data

data class Repository(
        val name: String,
        val fullName: String,
        val description: String,
        val forksCount: Int,
        val stargazersCount: Int,
        val owner: Owner
)