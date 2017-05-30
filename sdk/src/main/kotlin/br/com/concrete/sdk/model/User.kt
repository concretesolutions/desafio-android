package br.com.concrete.sdk.model

data class User(
        val login: String,
        val id: Long,
        val avatar_url: String,
        val score: Int
)