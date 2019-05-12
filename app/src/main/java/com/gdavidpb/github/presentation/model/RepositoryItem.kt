package com.gdavidpb.github.presentation.model

data class RepositoryItem(
    val id: String,
    val name: String,
    val fullName: String,
    val url: String,
    val description: String,
    val userLogin: String,
    val userUrl: String,
    val userAvatarUrl: String,
    val stargazersCount: String,
    val watchersCount: String,
    val openIssuesCount: String,
    val forksCount: String,
    val createdAt: String,
    val updatedAt: String
)