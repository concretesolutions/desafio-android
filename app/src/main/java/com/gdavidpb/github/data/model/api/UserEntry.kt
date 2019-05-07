package com.gdavidpb.github.data.model.api

data class UserEntry(
    val id: Long,
    val login: String,
    val url: String,
    val avatar_url: String?
)