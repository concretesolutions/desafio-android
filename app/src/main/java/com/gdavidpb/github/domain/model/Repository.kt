package com.gdavidpb.github.domain.model

import java.util.*

data class Repository(
    val id: Long,
    val name: String,
    val fullName: String,
    val url: String,
    val description: String,
    val owner: User,
    val stargazersCount: Int,
    val watchersCount: Int,
    val openIssuesCount: Int,
    val forksCount: Int,
    val createdAt: Date,
    val updatedAt: Date
)