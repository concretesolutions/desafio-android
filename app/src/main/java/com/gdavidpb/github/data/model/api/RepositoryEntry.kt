package com.gdavidpb.github.data.model.api

data class RepositoryEntry(
    val id: Long,
    val name: String,
    val url: String,
    val description: String,
    val owner: UserEntry,
    val watchers_count: Int,
    val open_issues_count: Int,
    val forks_count: Int,
    val created_at: String,
    val updated_at: String
)