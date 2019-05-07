package com.gdavidpb.github.domain.model

import java.util.*

data class Repository(
    val id: Long,
    val name: String,
    val url: String,
    val description: String,
    val owner: User,
    val watchersCount: Int,
    val openIssuesCount: Int,
    val forksCount: Int,
    val createdAt: Date,
    val updatedAt: Date
)