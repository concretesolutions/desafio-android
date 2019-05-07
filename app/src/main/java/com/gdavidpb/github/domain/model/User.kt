package com.gdavidpb.github.domain.model

data class User(
    val id: Long,
    val login: String,
    val url: String,
    val avatarUrl: String
)