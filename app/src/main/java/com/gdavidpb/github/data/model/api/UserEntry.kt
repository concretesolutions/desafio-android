package com.gdavidpb.github.data.model.api

data class UserEntry(
    val id: Long,
    val login: String,
    val html_url: String,
    val avatar_url: String?
)