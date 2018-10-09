package com.mbstro.fmoyagonzalez.desafio_android

import java.util.*

data class PullRequest(
        val title: String,
        val body: String,
        val updated_at: Date,
        val html_url: String,
        val user: User
)

data class User(
        val login: String,
        val avatar_url: String
)