package com.gdavidpb.github.domain.model

import java.util.*

data class Pull(
    val id: Long,
    val title: String,
    val body: String,
    val number: Int,
    val url: String,
    val user: User,
    val createdAt: Date,
    val updatedAt: Date,
    val closedAt: Date = Date(-1),
    val mergedAt: Date = Date(-1)
)