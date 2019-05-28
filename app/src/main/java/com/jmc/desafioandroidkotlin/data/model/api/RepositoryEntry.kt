package com.jmc.desafioandroidkotlin.data.model.api

data class RepositoryEntry(
    val id: String,
    val name: String,
    val full_name: String,
    val url: String,
    val description: String?,
    val owner: UserEntry,
    val stargazers_count: Int,
    val watchers_count: Int,
    val open_issues_count: Int,
    val forks_count: Int,
    val created_at: String,
    val updated_at: String
)