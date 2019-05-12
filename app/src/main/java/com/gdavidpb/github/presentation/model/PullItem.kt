package com.gdavidpb.github.presentation.model

data class PullItem(
    val id: Long,
    val title: String,
    val body: String,
    val number: Int,
    val url: String,
    val userLogin: String,
    val userUrl: String,
    val userAvatarUrl: String,
    val createdAt: String,
    val updatedAt: String,
    val closedAt: String,
    val mergedAt: String,
    val message: String = when {
        mergedAt != "-" -> "merged on $mergedAt"
        closedAt != "-" -> "closed on $closedAt"
        else -> "opened on $createdAt"
    }
)