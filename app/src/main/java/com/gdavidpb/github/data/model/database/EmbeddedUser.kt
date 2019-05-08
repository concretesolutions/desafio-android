package com.gdavidpb.github.data.model.database

data class EmbeddedUser(
    val id: Long,
    val login: String,
    val url: String,
    val avatarUrl: String
)