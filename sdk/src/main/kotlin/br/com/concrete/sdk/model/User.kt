package br.com.concrete.sdk.model

data class User(
        val login: String,
        val id: Long,
        val avatarUrl: String,
        val score: Int
)